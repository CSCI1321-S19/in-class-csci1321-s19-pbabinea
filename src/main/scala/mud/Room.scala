

package mud

import scala.io.Source
import scala.xml._


class Room(
  val name: String,
  val desc: String,
  private var items: List[Item],
  val exits: Array[Int]) { //the ints are the numbers for the rooms and are arranged directionally

  def description(): String = {
    "You are currently in " + name + ".\n" + desc + "\n\nThe items in this room are...\n" + itemListing
  }
  def itemListing(): String = {
    if (items.length == 0) {
      "Nothing."
    } else {
      items.map(item => item.name + "- " + item.desc + "\n").mkString("")
    }
  }
  def getExit(dir: Int): Option[Int] = {
    //0 = North, 1 = South, 2 = East, 3 = West, 4 = Up, 5 = Down
    if (exits(dir) == -1) None else Some(exits(dir))
  }

  def getItem(itemName: String): Option[Item] = {
    val foundItem = items.find(item => item.name.toLowerCase == itemName)
    foundItem match {
      case None => None
      case Some(a) => {
        items = items.patch(items.indexOf(a), Nil, 1)
        foundItem  
      }
    }
  }

  def dropItem(item: Item): Unit = {
    items ::= item
  }
}

object Room {
  val xmlData = XML.loadFile("map.xml")
  val rooms = (xmlData \ "room").map(roomFromNode).toArray
  
  def roomFromNode(n:Node):Room = {
    val name = (n \ "@name").text
    val desc = (n \ "description").text
    val items = (n \ "item").map(itemFromNode).toList
    val exits = (n \ "exits").text.split(" ").map(_.toInt)
    new Room(name, desc, items, exits)
  }
  
  def itemFromNode(n:Node):Item = {
    val name = (n \ "@name").text
    val desc = n.text
    new Item(name, desc)
  }
  
  //There is actually no need for this
  def nodeFromRoom(r:Room):Node = {
    <room name={r.name}>
			{r.desc}
			{r.items.map(i => <item name ={i.name}>{i.desc}</item>)}
			<exits>{r.exits.mkString(" ")}</exits>
		</room>
  }
  
}
