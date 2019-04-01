package Game.model.game_objects.BallStates

import Game.model.Player.Player
import Game.model.game_objects.InanimateObject

class BallStandingState extends BallState {
  override def use(inanimateObject: InanimateObject, player: Player): Unit = {
    inanimateObject.velocity.x = player.playerOrientation.x * player.standPower
    inanimateObject.velocity.y = player.playerOrientation.y * player.standPower
    inanimateObject.velocity.z = player.standPower
  }
}
