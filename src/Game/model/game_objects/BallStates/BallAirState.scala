package Game.model.game_objects.BallStates

import Game.model.Player.Player
import Game.model.game_objects.InanimateObject

class BallAirState extends BallState {
  override def use(inanimateObject: InanimateObject, player: Player): Unit = {
    inanimateObject.velocity.x = player.playerOrientation.x * player.airPower
    inanimateObject.velocity.y = player.playerOrientation.y * player.airPower
    inanimateObject.velocity.z = player.airPower
  }
}
