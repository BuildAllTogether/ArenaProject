package Game.model

import Game.model.Player._
import Game.model.environment._
import Game.model.game_objects._

class Game {

  val world = new World(10)

  val gridWidth: Double = 15
  val gridHeight: Double = 20
  val playerSize: Double = 0.5

  var killLine: Double = -0.1

  var platforms: List[Platform] = List(
    new Platform(new PhysicsVector(0, 0, 0), new PhysicsVector(gridWidth, 0, 0))
  )

  val minPlatformWidth = 1.0
  val maxPlatformWidth = 6.0
  val maxPlatformGaps = 8.0
  val probabilityOfNoPlatforms = 0.2
  val gapProbability = 0.8

  var lastLevelGenerated = 0

  //Just create 4 lines
  def createGoals(): Unit = {
    world.goalBars = world.goalBars :+ new Boundary(new PhysicsVector(0, 0, 0), new PhysicsVector(0, 0, 0))
  }

  createGoals()

  val player1 = new Player(
    new PhysicsVector(gridWidth / 3.0, 0, 0),
    new PhysicsVector(0, 0, 0),
    new PhysicsVector(0, 0, 0)
  )

  val player2 = new Player(
    new PhysicsVector(gridWidth * 2.0 / 3.0, 0, 0),
    new PhysicsVector(0, 0, 0),
    new PhysicsVector(0, 0, 0)
  )

  world.objects = List(player1, player2)

  def updateWorldAsPlayerRises(player: Player): Unit = {
    if (player.location.z > (killLine + gridHeight / 2.0)) {
      killLine = player.location.z - gridHeight / 2.0
    }
    if (player.location.z > lastLevelGenerated - gridHeight) {
      createGoals(lastLevelGenerated + gridHeight.toInt)
    }
  }

  def checkGoal(ball: Ball, name: String): Unit = {
    if (Physics.detectCollision(ball, newBallocation, platforms)) {
      player.state = new GameOver(player1)

      // Could add game states and transition to an EndGame state
      println(name + " fell")
    }
  }

  def update(deltaTime: Double): Unit = {
    Physics.updateWorld(this.world, deltaTime)
    player1.update(deltaTime)
    player2.update(deltaTime)

    updateWorldAsPlayerRises(player1)
    updateWorldAsPlayerRises(player2)

    checkGoal(player1, "Player 1")
    checkGoal(player2, "Player 2")
  }

}
