package Networking

import java.sql.{Connection, DriverManager, ResultSet}

import Game.model.Game

object Database {

  val url = "jdbc:mysql://localhost/mysql?serverTimezone=UTC"
  val username = "cow"
  val password = "milky"


  val driver = "com.mysql.cj.jdbc.Driver"
  Class.forName(driver).newInstance()

  var connection: Connection = DriverManager.getConnection(url, username, password)

  setupTable()

  def setupTable(): Unit = {
    val statement = connection.createStatement()
    statement.execute("CREATE TABLE IF NOT EXISTS players (username TEXT, total_score INT)")
  }

  def createPlayer(username: String): Unit = {
    val statement = connection.prepareStatement("INSERT INTO players VALUE (?, ?)")

    statement.setString(1, username)
    statement.setInt(2, 0)

    statement.execute()
  }


  def saveGame(username: String, total_score: Int): Unit = {
    val statement = connection.prepareStatement("UPDATE players SET total_score = ? WHERE username = ?")

    statement.setInt(1, total_score)
    statement.setString(2, username)

    statement.execute()
  }


  def loadGame(username: String, game: Game): Unit = {
    val statement = connection.prepareStatement("SELECT * FROM players WHERE username=?")
    statement.setString(1, username)
    val result: ResultSet = statement.executeQuery()

    result.next()
    game.total_score = result.getInt("total_score")
  }
}
