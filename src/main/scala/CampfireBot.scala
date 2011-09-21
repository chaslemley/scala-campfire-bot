package campfire

import handlers._

object CampfireBot {
  def main(args:Array[String]) {
    val room = new Room(437847)
    room.join
    room speak "pribot jumping in on the scene"

    // val f = new MoviesBot
    // val m = new Message(0, "pribot movie search Jack", "", "437847", "")
    // f ! m

    val processor = new StreamProcessor
    // register handlers
    processor.addHandler(new EchoBot)
    processor.addHandler(new ImageSearchBot)
    processor.addHandler(new MoviesBot)

    room.listen(processor)
  }
}