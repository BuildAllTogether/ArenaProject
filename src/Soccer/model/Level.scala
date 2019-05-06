package Soccer.model

object Level {

}

class Level {

  var wallLocations: List[GridLocation] = List(
    // Top Side
    new GridLocation(0,0),
    new GridLocation(1,0),
    new GridLocation(2,0),
    new GridLocation(3,0),
    new GridLocation(4,0),
    new GridLocation(5,0),
    new GridLocation(6,0),
    new GridLocation(7,0),
    new GridLocation(8,0),
    new GridLocation(9,0),
    new GridLocation(10,0),
    new GridLocation(11,0),
    new GridLocation(12,0),

    // Bottom Side
    new GridLocation(0,9),
    new GridLocation(1,9),
    new GridLocation(2,9),
    new GridLocation(3,9),
    new GridLocation(4,9),
    new GridLocation(5,9),
    new GridLocation(6,9),
    new GridLocation(7,9),
    new GridLocation(8,9),
    new GridLocation(9,9),
    new GridLocation(10,9),
    new GridLocation(11,9),
    new GridLocation(12,9),

    //Left Side
    new GridLocation(0,1),
    new GridLocation(0,2),

    //Spaces 3-6 left for goals

    new GridLocation(0,7),
    new GridLocation(0,8),

    //Right Side
    new GridLocation(12,1),
    new GridLocation(12,2),

    //Spaces 3-6 left for goals

    new GridLocation(12,7),
    new GridLocation(12,8)

  )
  var goalLocations: List[GridLocation] = List(
    new GridLocation(0,3),
    new GridLocation(0,4),
    new GridLocation(0,5),
    new GridLocation(0,6),

    //
    new GridLocation(12,3),
    new GridLocation(12,4),
    new GridLocation(12,5),
    new GridLocation(12,6)
  )

  var gridWidth: Int = 13
  var gridHeight: Int = 10
}
