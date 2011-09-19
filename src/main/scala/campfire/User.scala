package campfire

case class User(id:Int, name:String, email_address:String, avatar_url:String)

object User extends Item {
  def apply(id:Any): User = (HTTP.get("/users/"+id) \ "user").extract[User]
}