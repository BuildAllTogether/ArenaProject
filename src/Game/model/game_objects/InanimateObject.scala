package Game.model.game_objects

import Game.model.Player.Player
import Game.model.environment.PhysicsVector

abstract class InanimateObject(location: PhysicsVector, velocity: PhysicsVector)
  extends PhysicalObject(location, velocity) {
  var isHeld: Boolean = false

  def objectMass(): Double

  def use(player: Player): Unit

  def magnitudeOfMomentum(): Unit = {
    val magnitudeOfVelocity = Math.sqrt(
      Math.pow(this.velocity.x, 2.0) +
        Math.pow(this.velocity.y, 2.0) +
        Math.pow(this.velocity.z, 2.0)
    )
    magnitudeOfVelocity * this.objectMass()
  }
}
