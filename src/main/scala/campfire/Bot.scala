package campfire

import net.liftweb.json.JsonDSL._

class Bot {
  def speak(room:Room, message:String) {
    val data = 
      ("message" -> 
        ("type" -> "TextMessage") ~ 
        ("body" -> message))

    HTTP.post("/room/"+room.id+"/speak", data)
  }
}

object Bot extends Item {
  def apply(): Bot = (HTTP.get("/users/me") \ "user").extract[Bot]
}