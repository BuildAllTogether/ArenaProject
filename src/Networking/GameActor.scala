package Networking

import Game.model.Game
import akka.actor.Actor

case object Update

case object Save

case object Setup

class GameActor(usernames: String) extends Actor {

  var fourcharacters: Int = 0
  var the_game = new Game(usernames)

  override def receive: Receive = {
    case Setup =>
      for(user <- usernames) {
        if (Database.playerExists(user.toString)) {
          fourcharacters += 1
        }
        else {
          Database.createPlayer(user.toString)
            fourcharacters += 1
        }
      }
    case Update =>
      the_game.update(System.nanoTime())
   //   sender() ! GameState(the_game.toJSON())
    case Save =>
 //     for(user <- usernames) {
 //       Database.saveGame()
 //     }
  }
}
