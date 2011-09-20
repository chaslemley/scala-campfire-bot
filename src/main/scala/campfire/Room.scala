package campfire

import net.liftweb.json.JsonDSL._

case class Room(id:Int, name:String, topic:String, users:List[User]) {
  def speak(message:String) {
    val data = 
      ("message" -> 
        ("type" -> "TextMessage") ~ 
        ("body" -> message))

    HTTP.post("/room/"+id+"/speak", data)
  }

  def listen(processor:StreamProcessor) = HTTP.stream("/room/"+id+"/live", processor)
}

object Room extends Item {
  def apply(id:Any): Room = (HTTP.get("/room/"+id) \ "room").extract[Room]
}