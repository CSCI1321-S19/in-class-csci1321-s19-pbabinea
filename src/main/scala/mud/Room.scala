

package mud

import scala.io.Source
import scala.xml._


class Room(
  name: String,
  desc: String,
  private var items: List[Item],
  exits: Array[Int]) { //the ints are the numbers for the rooms

  def description(): String = ???

  def getExit(dir: Int): Option[Room] = {
    //0 = North, 1 = South, 2 = East, 3 = West, 4 = Up, 5 = Down
    if (exits(dir) == -1) None else Some(Room.rooms(exits(dir)))
  }

  def getItem(itemName: String): Option[Item] = ???

  def dropItem(item: Item): Unit = ???
}

object Room {
  val xmlData = XML.loadFile("map.xml")
  val rooms = (xmlData \ "room").map(roomFromNode).toArray
  
  def roomFromNode(n:Node):Room = {
    val name = (n \ "@name").text
    val desc = n.text
    val items = (n \ "item").map(itemFromNode).toList
    val exits = (n \ "exit").text.split(" ").map(_.toInt)
    new Room(name, desc, items, exits)
  }
  
  def itemFromNode(n:Node):Item = {
    val name = (n \ "@name").text
    val desc = n.text
    new Item(name, desc)
  }
  
}
