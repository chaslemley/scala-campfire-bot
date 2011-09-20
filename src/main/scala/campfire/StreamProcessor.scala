package campfire

import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferedReader

import net.liftweb.json

class StreamProcessor {
  def process(is: InputStream) {
    val reader: BufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))
    var line = reader.readLine()

    while (line != null) {
      var message = Message(json.parse(line))

      if (message.body startsWith "pribot") {
        // dispatch to any registered processors (maybe make processor an actor and send them a message ;))
        var room = Room(message.room_id)
        var user = User(message.user_id)
        room.speak("I do not understand that command "+user.first_name+"...")
      }
      println(message)
      line = reader.readLine()
    }

    is.close
  }
}