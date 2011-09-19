package campfire

case class Room(id:Int, name:String, topic:String, users:List[User])

object Room extends Item {
  def apply(id:Any): Room = (HTTP.get("/room/"+id) \ "room").extract[Room]
}