package campfire.handlers

import campfire._
import java.net.URLEncoder

class MoviesBot extends Handler {
  val search_command:String = "pribot movie search"
  val apiKey = ""
  
  implicit val formats = net.liftweb.json.DefaultFormats

  def handleMessage(m:Message):Unit = {
    if (m.body.startsWith(search_command)) {
      var q = m.body drop search_command.length
      println("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="+apiKey+"&q="+URLEncoder.encode(q.trim))
      var json = HTTP.get("http://api.rottentomatoes.com/api/public/v1.0/movies.json?page_limit=5&apikey="+apiKey+"&q="+URLEncoder.encode(q.trim))
      println(json)
    }

    exit
  }
}