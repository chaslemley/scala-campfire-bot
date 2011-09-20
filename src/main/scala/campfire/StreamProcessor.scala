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
      println(message)
      // dispatch to any registered processors (maybe make processor an actor and send them a message ;))
      line = reader.readLine()
    }

    is.close
  }
}