package Game.model.Player.player_states.air_states

import Game.model.Player.{Player, PlayerState}

class Air(player: Player) extends PlayerState(player) {

  override def aPressed(): Unit = {
    player.moveAMidAir()
  }

  override def dPressed(): Unit = {
    player.moveDMidAir()
  }

  override def wReleased(): Unit = {
    player.velocity.z /= 2.0
  }
}
