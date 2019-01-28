package mud

object main {
  println("What is your name?")
  val player = new Player(readLine, Array[Item](), ???)
  println("What would you like to do?")
  player.processCommand(readLine)
}