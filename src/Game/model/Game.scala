package Game.model

import Game.model.Player._
import Game.model.environment._
import Game.model.game_objects._

class Game {

  val world = new World(10)

  val gridWidth: Double = 20
  val gridHeight: Double = 15
  val playerSize: Double = 0.65

  var killLine: Double = -0.1

  var platforms: List[Boundary] = List(
    new Boundary(new PhysicsVector(0, 0, 0), new PhysicsVector(gridWidth, 0, 0)),
    new Boundary(new PhysicsVector(gridWidth * 1.0 / 11.0 - 1, 0, 0), new PhysicsVector(gridWidth * 1.0 / 11.0, 0, 0)),
    new Boundary(new PhysicsVector(gridWidth * 10.0 / 11.0 - 1, 0, 0), new PhysicsVector(gridWidth * 10.0 / 11.0, 0, 0))
//    new Boundary(new PhysicsVector(gridWidth * 10.0 / 11.0, 10, 10), new PhysicsVector(5, 5, 5))
    )

  val minPlatformWidth = 1.0
  val maxPlatformWidth = 6.0
  val maxPlatformGaps = 8.0
  val probabilityOfNoPlatforms = 0.2
  val gapProbability = 0.8

  var lastLevelGenerated = 0

  //Just create 4 lines
  def createGoals(): Unit = {
    world.goalBars = world.goalBars :+ new Boundary(new PhysicsVector(0, 0, 0), new PhysicsVector(1, 1, 1))
    world.goalBars = world.goalBars :+ new Boundary(new PhysicsVector(2, 2, 2), new PhysicsVector(3, 3, 3))
    world.goalNet = world.goalNet :+ new Boundary(new PhysicsVector(gridWidth * 1.0 / 11.0, 10, 10), new PhysicsVector(5, 5, 5))
    world.goalNet = world.goalNet :+ new Boundary(new PhysicsVector(gridWidth * 10.0 / 11.0, 10, 10), new PhysicsVector(7, 7, 7))
  }

  createGoals()

  val playerA1 = new Player(
    new PhysicsVector(gridWidth * 2.0 / 11.0, 0, 0),
    new PhysicsVector(0, 0, 0),
    new PhysicsVector(0, 0, 0)
  )

  val playerA2 = new Player(
    new PhysicsVector(gridWidth * 4.0 / 11.0, 0, 0),
    new PhysicsVector(0, 0, 0),
    new PhysicsVector(0, 0, 0)
  )

  val playerB1 = new Player(
    new PhysicsVector(gridWidth * 7.0 / 11.0, 0, 0),
    new PhysicsVector(0, 0, 0),
    new PhysicsVector(0, 0, 0)
  )

  val playerB2 = new Player(
    new PhysicsVector(gridWidth * 9.0 / 11.0, 0, 0),
    new PhysicsVector(0, 0, 0),
    new PhysicsVector(0, 0, 0)
  )

  val ball = new Ball(
    new PhysicsVector(gridWidth / 2.0, 0, 0),
    new PhysicsVector(0, 0, 0),
    5
  )

  world.objects = List(playerA1, playerA2, playerB1, playerB2)



  def checkGoal(ball: Ball): Unit = {
    if (Physics.detectCollision(ball, Physics.computePotentialLocation(ball, 0.016), world.goalNet.head) ||
      Physics.detectCollision(ball, Physics.computePotentialLocation(ball, 0.016), world.goalNet(1))) {

      // Could add game states and transition to an EndGame state
      //println("New Game")
    }
  }

  def update(deltaTime: Double): Unit = {
    Physics.updateWorld(this.world, deltaTime)
    playerA1.update(deltaTime)
    playerA2.update(deltaTime)

    checkGoal(ball)
  }

}
