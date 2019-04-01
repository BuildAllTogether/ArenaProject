package Game.model.environment

import Game.model.game_objects.{Boundary, PhysicalObject}

class World(val gravity: Double) {

  var objects: List[PhysicalObject] = List()
  var goalBars: List[Boundary] = List()
  var goalNet: List[Boundary] = List()

}
