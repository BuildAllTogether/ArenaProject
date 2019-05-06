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
        new GridLocation(13,0),
        new GridLocation(14,0),
        new GridLocation(15,0),
        new GridLocation(16,0),
        new GridLocation(17,0),
        new GridLocation(18,0),
        new GridLocation(19,0),
        new GridLocation(20,0),
        new GridLocation(21,0),
        new GridLocation(22,0),
        new GridLocation(23,0),
        new GridLocation(24,0),
        new GridLocation(25,0),
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
        new GridLocation(13,19),
        new GridLocation(14,19),
        new GridLocation(15,19),
        new GridLocation(16,19),
        new GridLocation(17,19),
        new GridLocation(18,19),
        new GridLocation(19,19),
        new GridLocation(20,19),
        new GridLocation(21,19),
        new GridLocation(22,19),
        new GridLocation(23,19),
        new GridLocation(24,19),
        new GridLocation(25,19),
        //Left Side
        new GridLocation(0,1),
        new GridLocation(0,2),
        new GridLocation(0,3),
        new GridLocation(0,4),
        new GridLocation(0,5),

        //Spaces 6-14 left for goals

        new GridLocation(0,14),
        new GridLocation(0,15),
        new GridLocation(0,16),
        new GridLocation(0,17),
        new GridLocation(0,18),
        new GridLocation(0,19),
        //Right Side
        new GridLocation(25,1),
        new GridLocation(25,2),
        new GridLocation(25,3),
        new GridLocation(25,4),
        new GridLocation(25,5),

        //Spaces 6-14 left for goals

        new GridLocation(25,14),
        new GridLocation(25,15),
        new GridLocation(25,16),
        new GridLocation(25,17),
        new GridLocation(25,18),
        new GridLocation(25,19)
      )
      goalLocations = List(
        new GridLocation(0,6),
        new GridLocation(0,7),
        new GridLocation(0,8),
        new GridLocation(0,9),
        new GridLocation(0,10),
        new GridLocation(0,11),
        new GridLocation(0,12),
        new GridLocation(0,13),
        //
        new GridLocation(25,6),
        new GridLocation(25,7),
        new GridLocation(25,8),
        new GridLocation(25,9),
        new GridLocation(25,10),
        new GridLocation(25,11),
        new GridLocation(25,12),
        new GridLocation(25,13),
      )
    }

  }

}

class Level {

  var wallLocations: List[GridLocation] = List()
  var goalLocations: List[GridLocation] = List()

  var gridWidth: Int = 26
  var gridHeight: Int = 20

  var base = new GridLocation(24, 3)


}
