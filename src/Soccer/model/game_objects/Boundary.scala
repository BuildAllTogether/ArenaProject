package Soccer.model.game_objects

import Soccer.model.physics.PhysicsVector

object Boundary {

  var nextID: Int = 0

}

class Boundary(var start:PhysicsVector, var end: PhysicsVector){
  val trueGoal: Boolean = true

  val platformID: Int = Boundary.nextID
  Boundary.nextID += 1

}
