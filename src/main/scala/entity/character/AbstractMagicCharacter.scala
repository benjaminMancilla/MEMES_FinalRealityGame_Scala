package entity.character


abstract class AbstractMagicCharacter extends AbstractCharacter with MagicCharacter {
  val _magic_points: Int
  var _current_magic_points: Int = _magic_points

  def magicPoints: Int = _magic_points
  def currentMagicPoints: Int = _current_magic_points
  def currentMagicPoints_=(newMp: Int) : Unit = {
    _current_magic_points = newMp
  }

}
