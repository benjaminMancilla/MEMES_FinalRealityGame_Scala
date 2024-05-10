package entity.character


abstract class AbstractMagicCharacter(name: String, hit_points: Int, defense: Int, weight: Int, magic_points: Int)
  extends AbstractCharacter(name: String, hit_points: Int, defense: Int, weight: Int) with MagicCharacter {
  private val _magic_points: Int = magic_points
  private var _current_magic_points: Int = _magic_points

  def magicPoints: Int = _magic_points
  def currentMagicPoints: Int = _current_magic_points
  def currentMagicPoints_=(newMp: Int) : Unit = {
    _current_magic_points = newMp
  }

}
