package campfire

import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferedReader
import handlers._

import net.liftweb.json

case class BackOff(var origBackOffTime: Long, capBackOffAt: Long) {
  var backOffTime = origBackOffTime

  def backOff = {
    Thread.sleep(backOffTime)
    backOffTime *= 2
    if(backOffTime > capBackOffAt) backOffTime = capBackOffAt
  }

  def reset() = { backOffTime = origBackOffTime }
}

class StreamProcessor {
  private var handlers = List[Handler]()

  def process(is: InputStream) {
    val reader: BufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))
    var line = reader.readLine().trim

    while(true) {
      var message = Message(json.parse(line))
      println(message)
      if (message.body != null && message.body.startsWith("pribot")) notifyHandlers(message)
      line = reader.readLine().trim
    }
  }

  def addHandler(handler:Handler) = handlers ::= handler
  def notifyHandlers(message:Message) = handlers foreach (_ ! message)
}