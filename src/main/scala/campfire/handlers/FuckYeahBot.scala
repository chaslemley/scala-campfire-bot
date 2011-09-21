package campfire.handlers

import scala.util.matching.Regex
import campfire._

class FuckYeahBot extends Handler {
  val command:String = "pribot fuck yeah"

  def handleMessage(m:Message):Unit = {
    if (m.body.startsWith(command)) {
      var queryString = m.body drop command.length
      var room = new Room(m.room_id.toInt)
      room speak "http://fuckyeah.herokuapp.com/"+queryString.trim+".jpg"
    }
  }
}