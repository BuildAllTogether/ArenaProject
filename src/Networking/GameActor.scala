package Networking

import Game.model.Game
import akka.actor.Actor

case object Update

case object Save

case object Setup

class GameActor extends Actor {

  var fourcharacters: Int = 0
  var the_game = new Game

  override def receive: Receive = {
    case Setup =>

    case Update =>
      the_game.update(System.nanoTime())
   //   sender() ! GameState(the_game.toJSON())
    case Save =>
 //     for(user <- usernames) {
 //       Database.saveGame()
 //     }
  }
}
