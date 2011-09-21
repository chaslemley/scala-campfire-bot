package campfire

import net.liftweb.json.JsonDSL._

case class Room(id:Int, name:String, topic:String, users:List[User]) {
  def speak(message:String) {
    val data = 
      ("message" -> 
        ("type" -> "TextMessage") ~ 
        ("body" -> message))

    Campfire.post("https://chaschats.campfirenow.com/room/"+id+"/speak.json", data)
  }

  def join = Campfire.post("https://chaschats.campfirenow.com/room/"+id+"/join.json", null)
  def leave = Campfire.post("https://chaschats.campfirenow.com/room/"+id+"/leave.json", null)
  def listen(processor:StreamProcessor) = Campfire.stream("http://streaming.campfirenow.com/room/"+id+"/live.json", processor)
  def this(id:Int) = this(id, "", "", null)
}

object Room extends Item {
  def apply(id:Any): Room = (Campfire.get("https://chaschats.campfirenow.com/room/"+id+".json") \ "room").extract[Room]
}