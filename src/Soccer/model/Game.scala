package Soccer.model

import play.api.libs.json.{JsValue, Json}
import Soccer.model.game_objects._
import Soccer.model.physics.{Physics, PhysicsVector, World}


class Game {

  val world: World = new World(10)

  var walls: List[Wall] = List()
  var goals: List[Goal] = List()
  var balls: List[PhysicalObject] = List(
    new PhysicalObject(
      new PhysicsVector(13, 10, 0),
      new PhysicsVector(0, 0, 0)
    )
  )

  var baseHealth = 10

  var level: Level = new Level()

  var players: Map[String, Player] = Map()
  var playersTeamA: Map[String, Player] = Map()
  var playersTeamB: Map[String, Player] = Map()
  val playerSize: Double = 0.3

  var lastUpdateTime: Long = System.nanoTime()


  def loadSoccerField(): Unit = {
    world.boundaries = List()

    balls.foreach(po => po.destroy())

    walls = List()
    goals = List()
    balls = List()

    blockTile(0, 0, level.gridWidth, level.gridHeight)

    level.wallLocations.foreach(wall => placeWall(wall.x, wall.y))
    level.goalLocations.foreach(wall => placeGoal(wall.x, wall.y))

  }


  def addPlayer(id: String): Unit = {
    val player = new Player(new PhysicsVector(3, 3, 0), new PhysicsVector(0, 0))
    players += (id -> player)
    world.objects = player :: world.objects
  }


  def removePlayer(id: String): Unit = {
    players(id).destroy()
    players -= id
  }

  def blockTile(x: Int, y: Int, width: Int = 1, height: Int = 1): Unit = {
    val ul = new PhysicsVector(x, y)
    val ur = new PhysicsVector(x + width, y)
    val lr = new PhysicsVector(x + width, y + height)
    val ll = new PhysicsVector(x, y + height)

    world.boundaries ::= new Boundary(ul, ur)
    world.boundaries ::= new Boundary(ur, lr)
    world.boundaries ::= new Boundary(lr, ll)
    world.boundaries ::= new Boundary(ll, ul)
  }


  def placeWall(x: Int, y: Int): Unit = {
    blockTile(x, y)
    walls = new Wall(x, y) :: walls
  }

  def goalTiles(x: Int, y: Int, width: Int = 1, height: Int = 1): Unit = {
    val ul = new PhysicsVector(x, y)
    val ur = new PhysicsVector(x + width, y)
    val lr = new PhysicsVector(x + width, y + height)
    val ll = new PhysicsVector(x, y + height)

    world.goals ::= new Boundary(ul, ur)
    world.goals ::= new Boundary(ur, lr)
    world.goals ::= new Boundary(lr, ll)
    world.goals ::= new Boundary(ll, ul)
  }

  def placeGoal(x: Int, y: Int): Unit = {
    blockTile(x, y)
    goals = new Goal (x, y) :: goals
  }


var dt: Double = 0.0
  def update(): Unit = {
    val time: Long = System.nanoTime()
    dt = (time - this.lastUpdateTime) / 1000000000.0
    Physics.updateWorld(this.world, dt)
    checkForGoals()
    balls = balls.filter(po => !po.destroyed)
    this.lastUpdateTime = time
  }

  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "gridSize" -> Json.toJson(Map("x" -> level.gridWidth, "y" -> level.gridHeight)),
      "walls" -> Json.toJson(this.walls.map({ w => Json.toJson(Map("x" -> w.x, "y" -> w.y)) })),
      "goals" -> Json.toJson(this.goals.map({ g => Json.toJson(Map("x" -> g.x, "y" -> g.y)) })),
      "players" -> Json.toJson(this.players.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.velocity.x),
        "v_y" -> Json.toJson(v.velocity.y),
        "team" -> Json.toJson(v.team),
        "id" -> Json.toJson(k)))})),
      "balls" -> Json.toJson(this.balls.map({ ball => Json.toJson(Map("x" -> ball.location.x, "y" -> ball.location.y))}))
    )

    Json.stringify(Json.toJson(gameState))
  }




  def checkForGoals(): Unit = {
    for (goal <- world.goals){
      for (ball <- balls){
        if (Physics.detectCollision(ball, Physics.computePotentialLocation(ball, dt), goal)){
          new Game
        }
      }
    }
  }
}
