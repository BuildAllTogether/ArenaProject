package Soccer.model.physics

import Soccer.model.game_objects.{Boundary, PhysicalObject}

class World(var gravity: Double) {

  var objects: List[PhysicalObject] = List()
  var boundaries: List[Boundary] = List()
  var goals: List[Boundary] = List()

}
