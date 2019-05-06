package Soccer.model.game_objects

class Goal (x: Int, y: Int) extends Wall (x, y){
  var crossed: Boolean = false
}
