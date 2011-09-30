package campfire

import handlers._

object CampfireBot {
  def main(args:Array[String]) {
    val room = new Room(437847)
    room.join
    room speak "pribot jumping in on the scene"

		//     val f = new BuildBot
		//     val m = new Message(0, "pribot deploy AG", "", "437847", "")
		//     f handleMessage m
		// return
		
    val processor = new StreamProcessor
    // register handlers
    processor.addHandler(new EchoBot)
    processor.addHandler(new ImageSearchBot)
    processor.addHandler(new MoviesBot)
    processor.addHandler(new BuildBot)

    room.listen(processor)
  }
}