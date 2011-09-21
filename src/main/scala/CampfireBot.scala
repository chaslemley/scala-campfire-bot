package campfire

import handlers._

object CampfireBot {
  def main(args:Array[String]) {
    val room = new Room(437847)
    room.join
    room speak "pribot jumping in on the scene"

    val processor = new StreamProcessor
    // register handlers
    processor.addHandler(new EchoBot)
    processor.addHandler(new FuckYeahBot)
    room.listen(processor)
  }
}