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
    var line = reader.readLine()

    while (line != null) {
      var message = Message(json.parse(line))
      notifyHandlers(message)
      // if (message.body startsWith "pribot") {
      //   // dispatch to any registered processors (maybe make processor an actor and send them a message ;))
        
      // }
      println(message)
      line = reader.readLine()
    }

    is.close
  }

  def addHandler(handler:Handler) = {
    handlers ::= handler
    handler.start
  }
  def notifyHandlers(message:Message) = handlers foreach (_ ! message)
}