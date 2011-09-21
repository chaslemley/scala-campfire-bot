package campfire.handlers

import campfire._
import java.net.URLEncoder

class MoviesBot extends Handler {
  val search_command:String = "pribot movie search"
  val apiKey = ""
  
  implicit val formats = net.liftweb.json.DefaultFormats
  
  case class Movie(title:String, year:String) {
    override def toString:String = title+" ("+year+")"
  }

  def handleMessage(m:Message):Unit = {
    if (m.body.startsWith(search_command)) {
      var q = m.body drop search_command.length
      var json = HTTP.get("http://api.rottentomatoes.com/api/public/v1.0/movies.json?page_limit=10&apikey="+apiKey+"&q="+URLEncoder.encode(q.trim))
      var movies = (json \ "movies").extract[List[Movie]]
      var room = new Room(m.room_id.toInt)
      var string = movies.map(_.toString).reduceLeft(_ + "\n" + _)
      room.paste(string)
    }
  }
}