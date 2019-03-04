package GUI

import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
import scalafx.scene.{Group, Scene}

object GUI2D extends JFXApp {

  val windowWidth: Double = 800
  val windowHeight: Double = 600

  val playerCircleRadius: Double = 20
  val playerSpeed: Double = 10

  val rectangleWidth: Double = 60
  val rectangleHeight: Double = 40

  var allRectangles: List[Shape] = List()
  var sceneGraphics: Group = new Group {}

  val player: Circle = new Circle {
    centerX = Math.random() * windowWidth
    centerY = Math.random() * windowHeight
    radius = playerCircleRadius
    fill = Color.Green
  }
  sceneGraphics.children.add(player)


  def drawRectangle(centerX: Double, centerY: Double): Unit = {
    val newRectangle = new Rectangle() {
      width = rectangleWidth
      height = rectangleHeight
      translateX = centerX - rectangleWidth / 2.0
      translateY = centerY - rectangleHeight / 2.0
      fill = Color.Blue
    }
    sceneGraphics.children.add(newRectangle)
    allRectangles = newRectangle :: allRectangles
  }


  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => player.translateY.value -= playerSpeed
      case "A" => player.translateX.value -= playerSpeed
      case "S" => player.translateY.value += playerSpeed
      case "D" => player.translateX.value += playerSpeed
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }


  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)

      // add an EventHandler[KeyEvent] to control player movement
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

      // add an EventHandler[MouseEvent] to draw a rectangle when the player clicks the screen
      addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => drawRectangle(event.getX, event.getY))
    }

    // define a function for the action timer (Could also use a method)
    // Rotate all rectangles (relies on frame rate. lag will slow rotation)
    val update: Long => Unit = (time: Long) => {
      for (shape <- allRectangles) {
        shape.rotate.value += 0.5
      }
    }

    // Start Animations. Calls update 60 times per second (takes update as an argument)
    AnimationTimer(update).start()
  }
}