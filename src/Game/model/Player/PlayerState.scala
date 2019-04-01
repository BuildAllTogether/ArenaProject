package Game.model.Player

abstract class PlayerState(player: Player) {

  var timeInState: Double = 0.0

  def update(dt: Double): Unit = {
    timeInState += dt

    if(player.aHeld){
      this.aPressed()
    }
    if(player.dHeld){
      this.dPressed()
    }
  }



  // API methods. Most methods do nothing by default. Only override methods that are needed for each state
  //Movement Behavior
  def aPressed(): Unit = {}

  def dPressed(): Unit = {}

  def wPressed(): Unit = {}

  def aReleased(): Unit = {
    player.stop()
  }

  def dReleased(): Unit = {
    player.stop()
  }

  def wReleased(): Unit = {}

  //Action Behavior
  def shoot(): Unit = {}
}
