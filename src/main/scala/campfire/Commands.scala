package campfire

import net.liftweb.json.JsonDSL._

object Commands {
  def speak(room:Room, message:String) {
    val data = 
      ("message" -> 
        ("type" -> "TextMessage") ~ 
        ("body" -> message))

    HTTP.post("/room/"+room.id+"/speak", data)
  }
}