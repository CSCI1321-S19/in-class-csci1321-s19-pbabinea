package mud

object main {
  def main(args:Array[String]):Unit = {
    val player = new Player(Nil, Room.rooms(0))
    println("This is a bunch of intro text.\n Well, what would you like to do?")
    var done = false
    while (!done){
      val input = readLine.toLowerCase
      if (input == "exit" || input == "quit") {
        println("\nIf you quit, you will start over in room 0 with no items next time you launch.\nAre you sure? (y/n)")
        if (readLine.toLowerCase == "n") {
          println("Okay.\nThen what would you like to do?")
        } else {
          println("Okay, bye bye!")
          done = true
        }
      }
      else player.processCommand(input)
    }  
  }
}