package Game.model.environment

import java.awt.geom.Line2D

import Game.model.Game
import _root_.Game.model.game_objects.{Ball, Boundary, PhysicalObject}



object Physics {
  def computePotentialLocation(obj: PhysicalObject, dt: Double): PhysicsVector = {
    val newX = obj.location.x + dt * obj.velocity.x
    val newY = obj.location.y + dt * obj.velocity.y
    val newZ = obj.location.z + dt * obj.velocity.z
    new PhysicsVector(newX, newY, newZ)
  }

  def updateVelocity(obj: PhysicalObject, world: World, dt: Double): Unit = {
    // Always apply gravity. Objects will adjust their velocity if they are on the ground
    obj.velocity.z = obj.velocity.z - world.gravity * dt
  }

  def detectCollision(thing: PhysicalObject, potLocation: PhysicsVector, edgy: Boundary): Boolean ={
    // Boundary Points
    val x1: Double = edgy.start.x; val y1: Double = edgy.start.y
    val x2: Double = edgy.end.x; val y2: Double = edgy.end.y
    // Location Lines
    val x3: Double = thing.location.x; val y3: Double = thing.location.y
    val x4: Double = potLocation.x; val y4: Double = potLocation.y
    // Create Boundary Lines
    val tallWall: Line2D = new Line2D.Double(x1, y1, x2, y2)
    val objectLine: Line2D = new Line2D.Double(x3, y3, x4, y4)
    // Check if lines intersect
    val result1: Boolean = objectLine.intersectsLine(tallWall)
    // Final Crossing Check
    var doNotCross: Boolean = true
    if (result1){
      doNotCross = false
    }
    doNotCross
  }

  def updateWorld(world: World, deltaTime: Double): Unit = {

    for (obj <- world.objects) {
      // update velocity
      updateVelocity(obj, world, deltaTime)

      // get potential location
      val potentialLocation = computePotentialLocation(obj, deltaTime)

      // check collisions
      var barCollision = false
      var goalCollision = false
      for (goals <- world.goalBars) {
        if (detectCollision(obj, potentialLocation, goals)) {
          barCollision = true
        }
      }
      for (goals <- world.goalNet) {
        if (detectCollision(obj, potentialLocation, goals)) {
          goalCollision = true
        }
      }

      obj match{
        case ball: Ball if goalCollision => new Game
        case ball: Ball if barCollision => new Game
        case _ =>
      }

    }
  }
}
