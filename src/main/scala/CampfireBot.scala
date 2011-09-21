package campfire

import handlers.EchoBot

object CampfireBot {
  def main(args:Array[String]) {
    val room = new Room(437847)
    room.join
    room speak "pribot jumping in on the scene"

    val processor = new StreamProcessor
    // register handlers
    val echo_bot = new EchoBot
    processor.addHandler(echo_bot)

    room.listen(processor)
  }
}