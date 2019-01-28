package mud

class Player(
  name: String,
  private var inventory: Array[Item],
  private var location: Room) {
  def processCommand(command: String): Unit = {
    command match {
      case "north" => move(0)
      case "south" => move(1)
      case "west" => move(2)
      case "east" => move(3)
      case "up" => move(4)
      case "down" => move(5)
      case "look" => println(location.description)
      case "inventory" => println(inventoryListing)
      case "get" => ???
      case "drop" => ???
      case "exit" => ???
      case "help" => ???
    }
  }
  def getFromInventory(itemName: String): Option[Item] = {
    ???
  }
  def inventoryListing(): String = {
    inventory.map(item => item.name + "- " + "item.desc\n").toString
  }
  def move(dir: Int): Unit = {
    //0 = North, 1 = South, 2 = East, 3 = West, 4 = Up, 5 = Down
    if (location.exits(dir) == None){
      println("You can't go this way.")
    }
    else {
      location = location.exits(dir)
    }
    
  }
}