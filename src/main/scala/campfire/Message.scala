package campfire

import net.liftweb.json.JValue

case class Message(id:Int, body:String, user_id: String, room_id: String, `type`:String)

object Message extends Item {
  def apply(data:JValue): Message = data.extract[Message]
}