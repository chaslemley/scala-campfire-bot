package campfire.handlers

import campfire._

class GuessMyNumberBot extends Handler {
  val R = new scala.util.Random
  var currentSecret = -1
  
  val StartGameCommand = "number guessing game"
  val GuessCommand = """is it ([\d]+)\?""".r

  def handleMessage(m:Message):Unit = {
    val room = new Room(m.room_id)
    m.body match {
      case StartGameCommand => startNewGame(room)
      case GuessCommand(guess) => makeAGuess(guess.toInt, room)
      case _ => 
    }
  }

  def startNewGame(room:Room) = {
    currentSecret = R.nextInt(100) + 1
    room speak "Ok let's play! I'm thinking of a number between 1 and 100."
  }

  def makeAGuess(guess:Int, room:Room) = {
    if ( currentSecret == -1 ) 
      room speak "Start a new game with `number guessing game`"
    else if ( guess < currentSecret )
      room speak "Nope, it's higher than "+guess+"."
    else if ( guess > currentSecret )
      room speak "Wrong! It's lower than "+guess+"."
    else if ( guess == currentSecret ) {
      room speak "YOU GOT IT! You win! It was "+guess+"."
      currentSecret = -1
    }
  }
}