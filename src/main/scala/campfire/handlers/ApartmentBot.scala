package campfire.handlers

import campfire._
import java.net.URLEncoder.encode

class ApartmentBot extends Handler {
  implicit val formats = net.liftweb.json.DefaultFormats
  def command = "pribot apartments"

  case class Apartment(name:String, adr: String) {
    override def toString = name+" "+adr
  }
  
  def handleMessage(m:Message) {
    if (m.body.startsWith(command)) {
      var q = m.body drop command.length
      var json = HTTP.get("http://www.apartmentguide.com/mobile/search?query="+encode(q))
      var apartments = (json \ "listings").extract[List[Apartment]]
      var room = new Room(m.room_id.toInt)
      room paste apartments.map(_.toString).reduceLeft(_ + "\n" + _)
    }
  }
}