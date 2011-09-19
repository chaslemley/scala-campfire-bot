package campfire

object CampfireBot {
  def main(args:Array[String]) {
    val room = Room(260694)
    val me = Bot()
    me.speak(room, "This is not a test, I swear")
    
    // me speak "hello" into room
    // me.listen(room, Handler)
  }
}