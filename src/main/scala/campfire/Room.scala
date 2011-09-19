package campfire

case class Room(id:Int, name:String, topic:String, users:List[User]) extends RemoteObject {
  def recentMessages():List[Message] = (fetchData("/room/"+id+"/recent") \ "messages").extract[List[Message]]
}

object Room extends RemoteObject {
  def apply(id:Any): Room = (fetchData("/room/"+id) \ "room").extract[Room]
}