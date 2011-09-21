package campfire.handlers

import campfire._
import java.net.URLEncoder

class ImageSearchBot extends Handler {
  implicit val formats = net.liftweb.json.DefaultFormats
  val command:String = "pribot image me"
  val r = new scala.util.Random

  def handleMessage(m:Message):Unit = {
    if (m.body.startsWith(command)) {
      var queryString = m.body drop command.length
      var json = HTTP.get("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="+URLEncoder.encode(queryString.trim))
      var imageURL = ((json \ "responseData" \ "results")(r.nextInt(3)) \ "url").extract[String]
      var room = new Room(m.room_id.toInt)
      room speak imageURL
      
    }
  }
}