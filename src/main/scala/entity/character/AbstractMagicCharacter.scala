package entity.character


/**
 * Abstract class representing a magic character entity in a game.
 * This class extends the AbstractCharacter class and implements the MagicCharacter trait.
 *
 * @param nameI          The name of the magic character.
 * @param hit_pointsI    The initial hit points of the magic character.
 * @param defenseI       The defense level of the magic character.
 * @param weightI        The weight of the magic character.
 * @param magic_pointsI  The total magic points of the magic character.
 */
abstract class AbstractMagicCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractCharacter(nameI, hit_pointsI, defenseI, weightI) with MagicCharacter {

  /**
   * The total magic points of the magic character.
   */
  private val _magic_points: Int = try {
    if (magic_pointsI >= 0) magic_pointsI else throw new IllegalArgumentException("Magic Points must be larger than or equal to 0")
  } catch {
    case _: IllegalArgumentException => 0
  }

  /**
   * The current magic points of the magic character.
   */
  private var _current_magic_points: Int = _magic_points

  /**
   * Gets the total magic points of the magic character.
   *
   * @return The total magic points of the magic character.
   */
  def magicPoints: Int = _magic_points

  /**
   * Gets the current magic points of the magic character.
   *
   * @return The current magic points of the magic character.
   */
  def currentMagicPoints: Int = _current_magic_points

  /**
   * Sets the current magic points of the magic character.
   *
   * @param newMp The new value for the current magic points.
   */
  def currentMagicPoints_=(newMp: Int): Unit = {
    _current_magic_points = newMp
  }
}

