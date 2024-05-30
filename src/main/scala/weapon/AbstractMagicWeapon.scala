package weapon
import effect.Effect
import exceptions.Require
import magic.Magic

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
  private val _magicAttack: Int = {Require.Stat(magicAttackI, "magicAttack") in (0 to 1000)}

  /** Retrieves the magical attack points of the magical weapon. */
  override def magicAttack: Int = _magicAttack

}

