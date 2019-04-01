package Game.view

import Game.controller.{ArrowInputs, WASDInputs}
import javafx.scene.input.KeyEvent
import Game.model.Game
import Game.model.game_objects.Platform
import Game.model.environment._
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.{Group, Scene}

object PlatformerGUI extends JFXApp {

  var lastUpdateTime: Long = System.nanotime()

  val game = new Game()

  val scaleFactor: Double = 30.0

  val windowWidth: Double = game.gridWidth * scaleFactor
  val windowHeight: Double = game.gridHeight * scaleFactor

  val playerSpriteSize: Double = game.playerSize
  val platformThickness = 0.1

  var platformSprites: Map[Int, Shape] = Map[Int, Shape]()

  var sceneGraphics: Group = new Group {}


  val player1Sprite: Shape = playerSprite(game.player1.location.x, game.player1.location.z, Color.Blue)
  val player2Sprite: Shape = playerSprite(game.player2.location.x, game.player2.location.z, Color.Red)

  sceneGraphics.children.add(player1Sprite)
  sceneGraphics.children.add(player2Sprite)

  // Convert game coordinated to GUI coordinates
  // We would use adapter if the GUI was using an interface for coordinates. Since the GUI wants us to set x and y
  // manually writing methods will suffice
  def convertX(gameX: Double, width: Double): Double = {
    (gameX - width / 2.0) * scaleFactor
  }

  def convertY(gameY: Double, height: Double): Double = {
    (game.gridHeight - (gameY - game.killLine) - height) * scaleFactor
  }


  def playerSprite(xLocation: Double, yLocation: Double, color: Color): Shape = {
    new Rectangle {
      width = playerSpriteSize * scaleFactor
      height = playerSpriteSize * scaleFactor
      translateX = convertX(xLocation, playerSpriteSize)
      translateY = convertY(yLocation, playerSpriteSize)
      fill = color
    }
  }



  def computeDistance(v1: PhysicsVector, v2: PhysicsVector): Double = {
    Math.sqrt(Math.pow(v1.x - v2.x, 2.0) + Math.pow(v1.z - v2.z, 2.0))
  }

  // assumes no angled platforms
  def platformSprite(platform: Platform): Rectangle = {
    val distance: Double = computeDistance(platform.start, platform.end)

    new Rectangle() {
      width = distance * scaleFactor
      height = platformThickness * scaleFactor
      translateX = convertX((platform.start.x + platform.end.x) / 2.0, distance)
      translateY = convertY(platform.start.z, platformThickness)
      fill = Color.Black
    }
  }


  this.stage = new PrimaryStage {
    this.title = "Climber"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)

      addEventHandler(KeyEvent.ANY, new WASDInputs(game.player1))
      addEventHandler(KeyEvent.ANY, new ArrowInputs(game.player2))
    }

    val update: Long => Unit = (time: Long) => {
      val dt: Double = (time - lastUpdateTime) / 1000000000.0
      lastUpdateTime = time
      game.update(dt)

      player1Sprite.translateX.value = convertX(game.player1.location.x, playerSpriteSize)
      player1Sprite.translateY.value = convertY(game.player1.location.z, playerSpriteSize)

      player2Sprite.translateX.value = convertX(game.player2.location.x, playerSpriteSize)
      player2Sprite.translateY.value = convertY(game.player2.location.z, playerSpriteSize)

      // platforms
      game.platforms.foreach((platform: Platform) => {
        if (platformSprites.contains(platform.platformID)) {

          val distance: Double = computeDistance(platform.start, platform.end)
          platformSprites(platform.platformID).translateX.value = convertX((platform.start.x + platform.end.x) / 2.0, distance)
          platformSprites(platform.platformID).translateY.value = convertY(platform.start.z, platformThickness)

        } else {
          val sprite: Rectangle = platformSprite(platform)
          sceneGraphics.children.add(sprite)
          platformSprites += (platform.platformID -> sprite)
        }
      })
    }

    // Start Animations. Calls update 60 times per second (takes update as an argument)
    AnimationTimer(update).start()
  }

}
