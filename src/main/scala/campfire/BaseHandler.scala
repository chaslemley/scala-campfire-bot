package campfire.handlers

import campfire._
import scala.actors.Actor
import scala.actors.Actor._

abstract class Handler extends Actor {
  start()
  def handleMessage(message:Message):Unit
  def act:Unit = {
    loop {
      receive {
        case m:Message => {
          handleMessage(m)
        }
      }
    }
  }
}