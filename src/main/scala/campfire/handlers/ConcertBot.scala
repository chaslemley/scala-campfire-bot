package campfire.handlers

import campfire._
import java.net.URLEncoder.encode

class ConcertBot extends Handler {
  val search_command:String = "pribot concerts"
  val apiKey = "bca6fc00614bca4056323f8bb6f187a0"
  
  implicit val formats = net.liftweb.json.DefaultFormats

  case class Venue(name: String)
  case class Artists(artists: List[String], headliner: String)
  case class Event(title: String, venue: Venue, artists: Artists, startDate: String) {
    override def toString = artists.headliner+" at "+venue.name+" on "+startDate
  }
    
  def handleMessage(m:Message):Unit = {
    if (m.body.startsWith(search_command)) {
      var q = m.body drop search_command.length
      var json = HTTP.get("http://ws.audioscrobbler.com/2.0/?method=geo.getevents&location="+encode(q.trim)+"&api_key="+apiKey+"&format=json&limit=15")
      var events = (json \ "events" \ "event").extract[List[Event]]
      var string = events.map(_.toString).reduceLeft(_ + "\n" + _)

      var room = new Room(m.room_id.toInt)
      room.paste(string)
    }
  }
}