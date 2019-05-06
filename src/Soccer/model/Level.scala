package Soccer.model

object Level {

  def apply(number: Int): Level ={
    new Level{
      wallLocations = List(
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
        new GridLocation(0,19),
        new GridLocation(1,19),
        new GridLocation(2,19),
        new GridLocation(3,19),
        new GridLocation(4,19),
        new GridLocation(5,19),
        new GridLocation(6,19),
        new GridLocation(7,19),
        new GridLocation(8,19),
        new GridLocation(9,19),
        new GridLocation(10,19),
        new GridLocation(11,19),
        new GridLocation(12,19),

        //Left Side
        new GridLocation(0,1),
        new GridLocation(0,2),

        //Spaces 3-6 left for goals

        new GridLocation(0,7),
        new GridLocation(0,8),

        //Right Side
        new GridLocation(25,1),
        new GridLocation(25,2),

        //Spaces 3-6 left for goals

        new GridLocation(25,7),
        new GridLocation(25,8)

      )
      goalLocations = List(
        new GridLocation(0,3),
        new GridLocation(0,4),
        new GridLocation(0,5),
        new GridLocation(0,6),

        //
        new GridLocation(25,3),
        new GridLocation(25,4),
        new GridLocation(25,5),
        new GridLocation(25,6)
      )
    }

  }

}

class Level {

  var wallLocations: List[GridLocation] = List()
  var goalLocations: List[GridLocation] = List()

  var gridWidth: Int = 13
  var gridHeight: Int = 10


}
