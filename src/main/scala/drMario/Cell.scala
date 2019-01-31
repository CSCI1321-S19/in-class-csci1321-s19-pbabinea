package drMario

trait Cell {
  def x: Int
  def y: Int
  def color: Int
  
  def supported():Boolean
}