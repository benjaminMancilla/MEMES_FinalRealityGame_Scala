package character

trait MagicCharacter extends Character {
  val _magic_points: Int
  var _current_magic_points: Int

  def magicPoints: Int
  def currentMagicPoints: Int
  def currentMagicPoints_=(newmp: Int) : Unit

}
