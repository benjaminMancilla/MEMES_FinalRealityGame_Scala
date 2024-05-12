package weapon

/**
 * Abstract class representing a specific type of magical weapon.
 * Magical weapons are a subtype of weapons and possess additional magical attack capabilities.
 * This class extends the functionality of the AbstractWeapon class and adds properties specific to magical weapons.
 *
 * @param nameI          The name of the magical weapon.
 * @param attackPointsI  The attack points of the magical weapon.
 * @param weightI        The weight of the magical weapon.
 * @param magicAttackI   The magical attack points of the magical weapon.
 */
abstract class AbstractMagicWeapon(nameI: String, attackPointsI: Int, weightI: Int, magicAttackI: Int)
  extends AbstractWeapon(nameI: String, attackPointsI: Int, weightI: Int) with MagicWeapon {

  /** The magical attack points of the magical weapon. */
  private val _magicAttack: Int = try {
    if (magicAttackI >= 0) magicAttackI else throw new IllegalArgumentException("Weapon magic attack can not be negative.")
  } catch {
    case _: IllegalArgumentException => 0
  }

  /** Retrieves the magical attack points of the magical weapon. */
  def magicAttack: Int = _magicAttack
}

