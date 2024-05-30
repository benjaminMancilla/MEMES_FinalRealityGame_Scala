package weapon

/**
 * Trait representing a magical weapon.
 * Magical weapons are a subtype of weapons and possess additional magical attack capabilities.
 */
trait PIMagicWeapon extends PIWeapon {

  /** The magical attack points of the weapon. Returns an Int */
  def magicAttack: Int
}
