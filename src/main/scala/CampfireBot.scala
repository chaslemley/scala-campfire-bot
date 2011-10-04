package campfire

import handlers._

object CampfireBot {
  def main(args:Array[String]) {
    val room = new Room(437847)
    room.join
    room speak "pribot jumping in on the scene"

    // val f = new GuessMyNumberBot
    // f handleMessage new Message(0, "number guessing game", "", "437847", "")
    // f handleMessage new Message(0, "is it 5?", "", "437847", "")
    // f handleMessage new Message(0, "is it 3?", "", "437847", "")
    // f handleMessage new Message(0, "is it 7?", "", "437847", "")
    // f handleMessage new Message(0, "is it 6?", "", "437847", "")
    // 
    val processor = new StreamProcessor
    // register handlers
    processor.addHandler(new EchoBot)
    processor.addHandler(new ImageSearchBot)
    processor.addHandler(new MoviesBot)
    processor.addHandler(new ConcertBot)
    processor.addHandler(new ApartmentBot)
    processor.addHandler(new GuessMyNumberBot)
    
    room.listen(processor)
  }
}