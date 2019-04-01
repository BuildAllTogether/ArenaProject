package Game.tests

import org.scalatest._
import Game.model.environment._
import Game.model.game_objects._

class TestUpdateVelocity extends FunSuite{
  test("Test Possible Velocity"){
    val deltaT: Double = 1.0
    val earth: World = new World(9.81)
    val listOfPhysicalObjects: List[PhysicalObject] = List(
      new PhysicalObject(new PhysicsVector(1.0, 1.0, 1.0), new PhysicsVector(1.0, 1.0, -4.0)),
      new PhysicalObject(new PhysicsVector(1.0, 1.0, 0.0), new PhysicsVector(1.0, 1.0, 2.0)),
      new PhysicalObject(new PhysicsVector(1.0, 1.0, 0.0), new PhysicsVector(1.0, 1.0, 20.0)),
      new PhysicalObject(new PhysicsVector(3.0, 5.0, 0.0), new PhysicsVector(6.0, -3.0, -2.0)),
      new PhysicalObject(new PhysicsVector(3.0, 5.0, 10.0), new PhysicsVector(6.0, -3.0, 10.0)),
      new PhysicalObject(new PhysicsVector(3.0, 5.0, 10.0), new PhysicsVector(6.0, 3.0, 10.0))
    )
    val expVelocityZ: List[Double] = List(
      -13.81,
      0.0,
      10.19,
      0.0,
      0.18,
      0.18
    )
    for (index <- listOfPhysicalObjects.indices){
      val currentIndex: PhysicalObject = listOfPhysicalObjects(index)
      Physics.updateVelocity(currentIndex, earth, deltaT)
      val velZ = (math floor currentIndex.velocity.z * 100) / 100
      assert(velZ == expVelocityZ(index), index)
    }
  }
}