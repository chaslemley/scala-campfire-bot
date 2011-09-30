package campfire.handlers

import scala.util.matching.Regex
import java.net._
import campfire._

class BuildBot extends Handler {
  val command:String = "pribot build"
  def handleMessage(m:Message):Unit = {
    if (m.body.startsWith(command)) {
      val room = new Room(m.room_id.toInt)
			val job = m.body drop (command.length+1)
			try {
				HTTP.get("http://dev-cc.nor.primedia.com:8080/hudson/job/" + job + "/build?delay=0sec")
	      room speak "jenkins is building: " + job
			} catch {
				case e: UnknownHostException => room paste "pribot is confused about the host: " + e.getMessage
			}			
    }
  }
}