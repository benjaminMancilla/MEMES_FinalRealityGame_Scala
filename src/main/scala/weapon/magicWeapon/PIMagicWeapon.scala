package weapon.magicWeapon

import weapon.PIWeapon

/**
 * Trait representing a magical weaponE.
 * Magical weapons are a subtype of weapons and possess additional magical attack capabilities.
 */
trait PIMagicWeapon extends PIWeapon {

  /** The magical attack points of the weaponE. Returns an Int */
  def magicAttack: Int
}
