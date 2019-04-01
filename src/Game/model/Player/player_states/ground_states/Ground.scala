package Game.model.Player.player_states.ground_states

import Game.model.Player.{Player, PlayerState}

abstract class Ground(player: Player) extends PlayerState(player) {
  override def aPressed(): Unit = {
    player.walkA()
    player.state = new Walking(player)
  }

  override def dPressed(): Unit = {
    player.walkD()
    player.state = new Walking(player)
  }

}
