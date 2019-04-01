package FrontEnd

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.{Group, Scene}

object PlayerField extends JFXApp {

  this.stage = new PrimaryStage {
    this.title = "Soccer"
    scene = new Scene(1000, 800)
    new Button("Start") {
      prefWidth = 125
    }


  }

}
