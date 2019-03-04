package BackEnd.entities

import BackEnd.environment._

object Main {

  def polymorphismExample(): Unit = {

    val soccerField: Boundary = new Boundary(new PhysicsVector(0.0, 0.0), new PhysicsVector(600.0, 800.0))

    val ball1: Ball = new Ball(new PhysicsVector(-2.28, 4.88),
      new PhysicsVector(-0.24, 8.59), 2)

    val ball2: Ball = new Ball(new PhysicsVector(10.325, -2.14),
      new PhysicsVector(3.65, -9.0), 5)

    val ball3: Ball = new Ball(new PhysicsVector(-6.988, 1.83),
      new PhysicsVector(-3.08, 5.4), 10)

    val gameObjects: List[PhysicalObject] = List(ball1, ball2, ball3)

    val world: World = new World(15)
    world.objects = gameObjects

    println(soccerField.start.toString)
    println(soccerField.end.toString)
    //    Physics.updateWorld(world, 0.0167)
  }

  def overrideExample(): Unit = {

  }

  def main(args: Array[String]): Unit = {
    polymorphismExample()
//    polymorphismExample()
//    overrideExample()
//    println(scala.io.Source.fromURL("http://api.open-notify.org/iss-now.json").mkString)
  }


}
