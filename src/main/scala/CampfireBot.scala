package campfire

object CampfireBot {
  def main(args:Array[String]) {
    val room = Room(437847)
    room.join
    room speak "pribot jumping in on the scene"

    val processor = new StreamProcessor
    // register handlers
    // echo_bot = new EchoBot
    // processor.registerHandler(echo_bot)

    room.listen(processor)
  }
}