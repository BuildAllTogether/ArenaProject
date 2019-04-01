package Game.model.game_objects.BallStates

import Game.model.Player.Player
import Game.model.game_objects.InanimateObject

trait BallState {
  def use(inanimateObject: InanimateObject, player: Player)
}
