package Soccer.model.game_objects

import Soccer.model.physics.PhysicsVector

object Platform {

  var nextID: Int = 0

}

class Platform(var start:PhysicsVector, var end: PhysicsVector){
  val trueGoal: Boolean = true

  val platformID: Int = Platform.nextID
  Platform.nextID += 1

}
