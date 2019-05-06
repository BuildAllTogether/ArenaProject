package Soccer.model.game_objects

import Soccer.model.game_objects.Player
import Soccer.model.game_objects.BallStates.{BallStandingState, BallState}
import Soccer.model.physics.PhysicsVector


class Ball(location: PhysicsVector,
           velocity: PhysicsVector,
           var mass: Double)
  extends InanimateObject(location, velocity) {

  var ballState: BallState = new BallStandingState


  def use(player: Player): Unit = {
    this.ballState.use(this, player)
  }

  override def objectMass(): Double = {
    this.mass
  }
}
