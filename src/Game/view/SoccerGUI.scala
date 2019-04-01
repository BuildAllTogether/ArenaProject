package Game.view

import java.io.PrintWriter

import Game.controller.{ArrowInputs, IJKLInputs, TFGHInputs, WASDInputs}
import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyEvent.ANY
import Game.model.Game
import _root_.Game.model.game_objects.Platform
import _root_.Game.model.environment.PhysicsVector
import javafx.event.ActionEvent
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.paint.Color.{ForestGreen, White, Yellow}
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.text.Text
import scalafx.scene.{Group, Scene}
import scalafx.stage.Stage

object SoccerGUI extends JFXApp {

  var lastUpdateTime: Long = System.nanoTime()

  val game = new Game()

  val scaleFactor: Double = 30

  val windowWidth: Double = game.gridWidth * scaleFactor
  val windowHeight: Double = game.gridHeight * scaleFactor

  val playerSpriteSize: Double = game.playerSize
  val platformThickness = 0.1

  var platformSprites: Map[Int, Shape] = Map[Int, Shape]()

  var sceneGraphics: Group = new Group {}


  val playerA1Sprite: Shape = playerSprite(game.playerA1.location.x, game.playerA1.location.z, Color.Blue)
  val playerA2Sprite: Shape = playerSprite(game.playerA2.location.x, game.playerA2.location.z, Color.Blue)
  val playerB1Sprite: Shape = playerSprite(game.playerB1.location.x, game.playerA1.location.z, Color.Red)
  val playerB2Sprite: Shape = playerSprite(game.playerB2.location.x, game.playerA2.location.z, Color.Red)

  sceneGraphics.children.add(playerA1Sprite)
  sceneGraphics.children.add(playerA2Sprite)
  sceneGraphics.children.add(playerB1Sprite)
  sceneGraphics.children.add(playerB2Sprite)


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

  var the_gamewindow = new Stage

  the_gamewindow = new PrimaryStage {
    this.title = "Soccer"


    scene = new Scene(1000, 800) {


      fill = ForestGreen

      val img = new Image("http://www.how-to-draw-cartoons-online.com/image-files/xcartoon_soccer.gif.pagespeed.ic.gm0gFBQIPL.png")

      val menu = new ImageView(img)

      menu.layoutX = 370
      menu.layoutY = 250


      val word = new HBox {
        children = Seq(
          new Text("Soccer  Shooters") {

            style = "-fx-font-size: 100pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(White, Yellow))
          })
      }


      val button = new Button("Start Game")
      button.layoutX = 455
      button.layoutY = 600


      val textField = new TextField
      textField.layoutX = 418
      textField.layoutY = 550
      textField.promptText = "Username"


      button.onAction = (event: ActionEvent) => {
        println("Button clicked.")
        println(textField.getText)
        the_gamewindow.setScene(scene1)
      }


      content = List(button, textField, word, menu)

    }


    var scene1 = new Scene(windowHeight, windowHeight) {
      content = List(sceneGraphics)

      addEventHandler(ANY, new WASDInputs(player = game.playerA1))
      addEventHandler(ANY, new ArrowInputs(player = game.playerA2))
      addEventHandler(ANY, new IJKLInputs(player = game.playerB1))
      addEventHandler(ANY, new TFGHInputs(player = game.playerB2))

      val update: Long => Unit = (time: Long) => {
        val dt: Double = (time - lastUpdateTime) / 1000000000.0
        lastUpdateTime = time
        game.update(dt)

        playerA1Sprite.translateX.value = convertX(game.playerA1.location.x, playerSpriteSize)
        playerA1Sprite.translateY.value = convertY(game.playerA1.location.z, playerSpriteSize)

        playerA2Sprite.translateX.value = convertX(game.playerA2.location.x, playerSpriteSize)
        playerA2Sprite.translateY.value = convertY(game.playerA2.location.z, playerSpriteSize)

        playerA1Sprite.translateX.value = convertX(game.playerB1.location.x, playerSpriteSize)
        playerA1Sprite.translateY.value = convertY(game.playerB1.location.z, playerSpriteSize)

        playerA2Sprite.translateX.value = convertX(game.playerB2.location.x, playerSpriteSize)
        playerA2Sprite.translateY.value = convertY(game.playerB2.location.z, playerSpriteSize)

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
}
