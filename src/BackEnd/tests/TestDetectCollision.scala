package BackEnd.tests

import BackEnd.environment._
import org.scalatest._

class TestDetectCollision extends FunSuite{
  test("Test Possible Location"){
    val wall1: Boundary = new Boundary(new PhysicsVector(0.0, 5.0, 1.0), new PhysicsVector(5.0, 5.0, 3.0))

    val orgLocation1: PhysicalObject = new PhysicalObject(new PhysicsVector(0.0, 0.0, 0.0), new PhysicsVector(0.0, 0.0, 0.0))
    val potLocation1: PhysicsVector = new PhysicsVector(5.0, 5.0, 5.0)

    assert(!Physics.detectCollision(orgLocation1, potLocation1, wall1))


    val wall2: Boundary = new Boundary(new PhysicsVector(-1.0, -1.0, 1.0), new PhysicsVector(3.0, 1.0, 3.0))

    val orgLocation2: PhysicalObject = new PhysicalObject(new PhysicsVector(0.0, 3.0, 0.0), new PhysicsVector(0.0, 0.0, 0.0))
    val potLocation2: PhysicsVector = new PhysicsVector(5.0, 2.0, 5.0)

    assert(Physics.detectCollision(orgLocation2, potLocation2, wall2))
    }
  }