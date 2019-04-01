package Game.tests

import org.scalatest._
import Game.model.environment._
import Game.model.game_objects._

class TestComputeLocation extends FunSuite{
  test("Test Possible Location"){
    val deltaT: Double = 1.0
    val listOfPhysicalObjects: List[PhysicalObject] = List(
      new PhysicalObject(new PhysicsVector(1.0, 1.0, 1.0), new PhysicsVector(1.0, 1.0, -4.0)),
      new PhysicalObject(new PhysicsVector(1.0, 1.0, 0.0), new PhysicsVector(1.0, 1.0, 2.0)),
      new PhysicalObject(new PhysicsVector(3.0, 5.0, 0.0), new PhysicsVector(6.0, -3.0, 2.0)),

    )
    val endLocation: List[PhysicsVector] = List(
      new PhysicsVector(2.0, 2.0, 0.0),
      new PhysicsVector(2.0, 2.0, 2.0),
      new PhysicsVector(9.0, 2.0, 2.0)
    )
    for (index <- listOfPhysicalObjects.indices){
      assert(Physics.computePotentialLocation(listOfPhysicalObjects(index), deltaT).x == endLocation(index).x)
      assert(Physics.computePotentialLocation(listOfPhysicalObjects(index), deltaT).y == endLocation(index).y)
      assert(Physics.computePotentialLocation(listOfPhysicalObjects(index), deltaT).z == endLocation(index).z)
    }
    for (index <- listOfPhysicalObjects.indices){
      if (listOfPhysicalObjects(index).location.z + listOfPhysicalObjects(index).velocity.z * deltaT < 0) {
        assert(Physics.computePotentialLocation(listOfPhysicalObjects(index), deltaT).z == 0)
      }
    }
  }
}