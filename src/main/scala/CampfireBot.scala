package campfire

object CampfireBot {
  def main(args:Array[String]) {
    val room = Room(205988)
    // room.speak("test")
    val processor = new StreamProcessor

    // register handlers
    // echo_bot = new EchoBot
    // processor.registerHandler(echo_bot)

    room.listen(processor)
  }
}