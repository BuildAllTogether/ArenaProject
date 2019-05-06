package Soccer.model

import akka.actor.{Actor, ActorRef}
import Soccer.model.physics.PhysicsVector


class GameActor extends Actor {

  var playersTeamA: Map[String, ActorRef] = Map()

  val game: Game = new Game()
  loadSoccerField()

  def loadSoccerField(): Unit ={
    val level: Level = new Level
    game.loadSoccerField(level)
  }

  override def receive: Receive = {
    case message: AddPlayer => game.addPlayer(message.username)
    case message: RemovePlayer => game.removePlayer(message.username)
    case message: MovePlayer => game.players(message.username).move(new PhysicsVector(message.x, message.y))
    case message: StopPlayer => game.players(message.username).stop()

    case UpdateGame =>
      game.update()
      //Add Goal Checker Here
      for (goal <- game.goals){
        if (goal.crossed){
          loadSoccerField()
        }
      }
    case SendGameState => sender() ! GameState(game.gameState())
  }
}
