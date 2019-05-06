package Soccer.model.game_objects

import Soccer.model.physics.PhysicsVector

class Projectile(location: PhysicsVector,
                 velocity: PhysicsVector)
  extends PhysicalObject(location, velocity) {


  override def onGround():Unit={
    this.destroy()
  }

  override def collide(): Unit = {
    this.destroy()
  }

}
