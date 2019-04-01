package Game.controller

import Game.model.Player.Player
import javafx.event.EventHandler
import javafx.scene.input.KeyEvent

abstract class KeyboardInputs(player: Player) extends EventHandler[KeyEvent] {

  val LEFT: String
  val RIGHT: String
  val JUMP: String

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode.getName match {
        case this.LEFT => player.aReleased()
        case this.RIGHT => player.dReleased()
        case this.JUMP => player.wReleased()
        case _ =>
      }
      case "KEY_PRESSED" => keyCode.getName match {
        case this.LEFT => player.aPressed()
        case this.RIGHT => player.dPressed()
        case this.JUMP => player.wPressed()
        case _ =>
      }
      case _ =>
    }
  }
}

class WASDInputs(player: Player) extends KeyboardInputs(player) {
  override val LEFT: String = "A"
  override val RIGHT: String = "D"
  override val JUMP: String = "W"
}


class ArrowInputs(player: Player) extends KeyboardInputs(player) {
  override val LEFT: String = "Left"
  override val RIGHT: String = "Right"
  override val JUMP: String = "Up"
}

class IJKLInputs(player: Player) extends KeyboardInputs(player) {
  override val LEFT: String = "J"
  override val RIGHT: String = "L"
  override val JUMP: String = "I"
}

class TFGHInputs(player: Player) extends KeyboardInputs(player) {
  override val LEFT: String = "F"
  override val RIGHT: String = "H"
  override val JUMP: String = "T"
}