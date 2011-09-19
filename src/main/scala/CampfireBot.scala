package campfire

object CampfireBot {
  def main(args:Array[String]) {
    val room = Room(260694)
    val bot = Bot()
    bot.speak(room, "This is not a test, I swear")
    
    // bot speak "hello" into room
  }
}