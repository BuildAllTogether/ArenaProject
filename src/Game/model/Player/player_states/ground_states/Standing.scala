package Game.model.Player.player_states.ground_states

import Game.model.Player.Player
import Game.model.Player.player_states.air_states.Air

class Standing(player: Player) extends Ground(player) {
  override def wPressed(): Unit = {
    player.velocity.z = player.standJump
    player.state = new Air(player)
  }
}
