package Game.model.Player

import Game.model.Player.player_states.ground_states.Standing
import Game.model.environment.PhysicsVector
import Game.model.game_objects.{InanimateObject, PhysicalObject}

class Player(playerLocation: PhysicsVector, playerVelocity: PhysicsVector, var playerOrientation: PhysicsVector)
  extends PhysicalObject(playerLocation, playerVelocity) {

  val walkSpeed: Double = 5.0
  val airSpeed: Double = 4.0

  val standJump: Double = 6.0
  val walkJump: Double = 8.0

  val standPower: Double = 8.0
  val runPower: Double = 10.0
  val airPower: Double = 6.0

  var state: PlayerState = new Standing(this)

  var aHeld = false
  var dHeld = false
  var wHeld = false

  // Begin API methods
  //Pressing A
  def aPressed(): Unit = {
    this.aHeld = true
    this.state.aPressed()
  }

  //Pressing D
  def dPressed(): Unit = {
    this.dHeld = true
    this.state.dPressed()
  }

  //Pressing W or Space
  def wPressed(): Unit = {
    this.wHeld = true
    this.state.wPressed()
  }

  //Releasing A
  def aReleased(): Unit = {
    this.aHeld = false
    this.state.aReleased()
  }

  //Releasing D
  def dReleased(): Unit = {
    this.dHeld = false
    this.state.dReleased()
  }

  //Releasing W or Space
  def wReleased(): Unit = {
    this.wHeld = false
    this.state.wReleased()
  }

//Updates the state functions that the player is in
  def update(dt: Double): Unit = {
    this.state.update(dt)
  }
  // End API methods


  // 5 helper methods that will be called by multiple states. Methods added here to avoid rewriting them in each state
  // where they are called

  def walkA(): Unit = {
    this.velocity.x = -this.walkSpeed
  }

  def walkD(): Unit = {
    this.velocity.x = this.walkSpeed
  }


  // Adjust to air speed when in air. Only applies if the direction is changed mid air
  def moveAMidAir(): Unit = {
    this.velocity.x = this.velocity.x.min(-this.airSpeed)
  }

  def moveDMidAir(): Unit = {
    this.velocity.x = this.velocity.x.max(this.airSpeed)
  }


  def stop(): Unit = {
    this.velocity.x = 0.0
  }

  //Now that movement is over, onto attacking functions
  // Attaching a ball to a player when it comes close
  def attachBall(item: InanimateObject): Unit = {
    if (!item.isHeld){
      if (this.playerLocation.x - item.location.x < 10 && this.playerLocation.y - item.location.y < 10 && this.playerLocation.z - item.location.z < 10){
        item.isHeld = true
      }
    }
    else{
    }

  }

  def shootBall(item: InanimateObject): Unit = {
    item.use(this)
  }
}
