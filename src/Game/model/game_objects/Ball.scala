package Game.model.game_objects

import Game.model.Player.Player
import Game.model.environment.PhysicsVector
import Game.model.game_objects.BallStates._

class Ball(override val location: PhysicsVector,
           override val velocity: PhysicsVector,
           val mass: Double)
  extends InanimateObject(location, velocity) {

  var ballState: BallState = new BallStandingState


  def use(player: Player): Unit = {
    this.ballState.use(this, player)
  }

  override def objectMass(): Double = {
    this.mass
  }
}
