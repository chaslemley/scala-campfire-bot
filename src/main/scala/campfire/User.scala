package campfire

case class User(id:Int, name:String, email_address:String, avatar_url:String)

object User extends RemoteObject {
  def apply(id:Any): User = (fetchData("/users/"+id) \ "user").extract[User]
}