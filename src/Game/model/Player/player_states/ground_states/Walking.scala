package Game.model.Player.player_states.ground_states

import Game.model.Player.Player
import Game.model.Player.player_states.air_states.Air

class Walking(player: Player) extends Ground(player) {
  override def aPressed(): Unit = {
    player.walkA()
  }

  override def dPressed(): Unit = {
    player.walkD()
  }

  override def wPressed(): Unit = {
    player.velocity.z = player.walkJump
    player.state = new Air(player)
  }

  override def aReleased(): Unit = {
    super.aReleased()
    player.state = new Standing(player)
  }

  override def dReleased(): Unit = {
    super.dReleased()
    player.state = new Standing(player)
  }
}
