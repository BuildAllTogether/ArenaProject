package Game.model.Player

abstract class PlayerState(player: Player) {

  var timeInState: Double = 0.0

  def update(dt: Double): Unit = {
    timeInState += dt

    if(player.aHeld){
      println("Left Held")
      this.aPressed()
    }
    if(player.dHeld){
      println("Right Held")
      this.dPressed()
    }
  }



  // API methods. Most methods do nothing by default. Only override methods that are needed for each state
  //Movement Behavior
  def aPressed(): Unit = {
    println("Left Pressed")
  }

  def dPressed(): Unit = {
    println("Right Pressed")
  }

  def wPressed(): Unit = {
    println("Jump Pressed")
  }

  def aReleased(): Unit = {
    println("Left Released")
    player.stop()
  }

  def dReleased(): Unit = {
    println("Right Released")
    player.stop()
  }

  def wReleased(): Unit = {
    println("Jump Released")
  }

  //Action Behavior
  def shoot(): Unit = {}
}
