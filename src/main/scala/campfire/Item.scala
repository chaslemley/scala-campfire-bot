package campfire

trait Item {
  implicit val formats = net.liftweb.json.DefaultFormats
}