package Game.model.game_objects

import Game.model.environment.PhysicsVector

object Boundary {

  var nextID: Int = 0

}

class Boundary(var start:PhysicsVector, var end: PhysicsVector){
  val trueGoal: Boolean = true

  val platformID: Int = Boundary.nextID
  Boundary.nextID += 1

}
