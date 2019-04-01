package Game.model.game_objects.BallStates

import Game.model.Player.Player
import Game.model.game_objects.InanimateObject

class BallRunningState extends BallState {
  override def use(inanimateObject: InanimateObject, player: Player): Unit = {
    inanimateObject.velocity.x = player.playerOrientation.x * player.runPower
    inanimateObject.velocity.y = player.playerOrientation.y * player.runPower
    inanimateObject.velocity.z = player.runPower
  }
}
