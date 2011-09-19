package campfire

case class Room(id:Int, name:String, topic:String, users:List[User]) extends Item {
  def recentMessages():List[Message] = (HTTP.get("/room/"+id+"/recent") \ "messages").extract[List[Message]]
}

object Room extends Item {
  def apply(id:Any): Room = (HTTP.get("/room/"+id) \ "room").extract[Room]
}