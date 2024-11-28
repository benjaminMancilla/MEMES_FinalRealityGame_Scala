package model.weapon.magicWeapon

import exceptions.Require
import model.weapon.AbstractWeapon

/**
 * Abstract class representing a specific type of magical weaponE.
 * Magical weapons are a subtype of weapons and possess additional magical attack capabilities.
 * This class extends the functionality of the AbstractWeapon class and adds properties specific to magical weapons.
 *
 * @param nameI          The name of the magical weaponE.
 * @param attackPointsI  The attack points of the magical weaponE.
 * @param weightI        The weight of the magical weaponE.
 * @param magicAttackI   The magical attack points of the magical weaponE.
 */
abstract class AbstractMagicWeapon(nameI: String, attackPointsI: Int, weightI: Int, magicAttackI: Int)
  extends AbstractWeapon(nameI: String, attackPointsI: Int, weightI: Int) with MagicWeapon {

  /** The magical attack points of the magical weaponE. */
  private val _magicAttack: Int = {Require.Stat(magicAttackI, "magicAttack") in (0 to 1000)}

  /** Retrieves the magical attack points of the magical weaponE. */
  override def magicAttack: Int = _magicAttack

}

