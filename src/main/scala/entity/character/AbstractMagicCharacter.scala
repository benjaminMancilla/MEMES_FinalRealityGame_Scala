package entity.character


abstract class AbstractMagicCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int) with MagicCharacter {
  private val _magic_points: Int = try {
    if (magic_pointsI>=0) magic_pointsI else throw new IllegalArgumentException("Magic Points must be larger than -1")
  } catch {
    case _: IllegalArgumentException => 0
  }
  private var _current_magic_points: Int = _magic_points

  def magicPoints: Int = _magic_points
  def currentMagicPoints: Int = _current_magic_points
  def currentMagicPoints_=(newMp: Int) : Unit = {
    _current_magic_points = newMp
  }

}
