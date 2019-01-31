package mud

class Player(
  private var inventory: List[Item],
  private var location: Room) {
  def processCommand(command: String): Unit = {
    command match {
      case "north" => move(0)
      case "n" => move(0)
      case "south" => move(1)
      case "s" => move(1)
      case "east" => move(2)
      case "e" => move(2)
      case "west" => move(3)
      case "w" => move(3)
      case "up" => move(4)
      case "down" => move(5)
      case "look" => println(location.description)
      case "inventory" => println(inventoryListing)
      //case "get" => location.getItem(???)
      //case "drop" => location.dropItem(???)
      case "ur mom" => println("no u")
      case "help" => println(help)
      case _ => {
        if (command.substring(0,3) == "get") {
          val foundItem = location.getItem(command.substring(command.indexOf(" ")+1))
          foundItem match {
            case None => println("\nItem not in room")
            case Some(a) => addToInv(a)
          }
        }
        else if (command.substring(0,4) == "drop") getFromInventory(command.substring(command.indexOf(" ")+1)) 
        else println("Not a valid command.")
      }
    }
  }
  def getFromInventory(itemName: String): Option[Item] = {
    val foundItem = inventory.find(item => item.name.toLowerCase == itemName)
    foundItem match {
      case None => None
      case Some(a) => {
        inventory = inventory.patch(inventory.indexOf(a), Nil, 1)
        location.dropItem(a)
        println("\nYou dropped " + a.name + ".")
        foundItem
      }
    }
  }
  def inventoryListing(): String = {
    if (inventory.length == 0) {
      "\nYou don't have anything."
    } else {
      "\n" + inventory.map(item => item.name + "- " + item.desc + "\n").mkString("")
    }
  }
  def move(dir: Int): Unit = {
    //0 = North, 1 = South, 2 = East, 3 = West, 4 = Up, 5 = Down
    if (location.getExit(dir) == None){
      println("You can't go this way.")
    }
    else {
      location = Room.rooms(location.exits(dir))
      println(location.description)
    }
    
  }
  def addToInv(i:Item):Unit = {
    println("Picked up " + i.name + ".")
    inventory ::= i
  }
  
  def help(): String = {
    """The commands you can use are:
      moving- north, south, east, west, up, or down
        look- Shows your current location's description
   inventory- Shows your current inventory
  get [item]- Pick up an item from the room
 drop [item]- Drop an item in the room
exit or quit- Quits the game
        help- Pulls up this help menu!"""
  }
}