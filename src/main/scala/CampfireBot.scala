package campfire

import campfire.Commands._

object CampfireBot {
  def main(args:Array[String]) {
    val room = Room(205988)
    speak(room, "k")
  }
}