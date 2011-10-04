package campfire

import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferedReader
import handlers._

import net.liftweb.json

class StreamProcessor {
  private var handlers = List[Handler]()

  def process(is: InputStream) {
    val reader: BufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))
    var line = reader.readLine().trim

    while(true) {
      var message = Message(json.parse(line))
      println(message)
      if (message.body != null) notifyHandlers(message)
      line = reader.readLine().trim
    }
  }

  def addHandler(handler:Handler) = {
    handlers ::= handler
    handler start
  }
  def notifyHandlers(message:Message) = handlers foreach (_ ! message)
}