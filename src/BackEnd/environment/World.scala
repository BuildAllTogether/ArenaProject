package BackEnd.environment

class World(val gravity: Double) {

  var objects: List[PhysicalObject] = List()
  var boundaries: List[Boundary] = List()

}
