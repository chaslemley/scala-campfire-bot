package campfire

class Bot extends RemoteObject {
  def speak(room:Room, message:String) {
    println(message)
    postData("/room/"+room.id+"/speak")
  }
}

object Bot extends RemoteObject {
  def apply(): Bot = (fetchData("/users/me") \ "user").extract[Bot]
}