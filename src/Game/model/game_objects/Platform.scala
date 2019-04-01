package Game.model.game_objects

import Game.model.environment.PhysicsVector

object Platform {

  var nextID: Int = 0

}

class Platform(var start:PhysicsVector, var end: PhysicsVector){
  val trueGoal: Boolean = true

  val platformID: Int = Platform.nextID
  Platform.nextID += 1

}
