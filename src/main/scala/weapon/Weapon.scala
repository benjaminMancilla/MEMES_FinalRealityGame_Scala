package weapon

import entity.character.Character

/**
 * Trait representing a specific weapon.
 * Weapons are equipment that characters can use in the game.
 * Compatibility with character types determines whether a weapon can be equipped by a specific character.
 * Weapons can either be ordinary or magical.
 * Magical weapons can be used to cast spells.
 * Each weapon can only be equipped by one character at a time.
 * None in equipped weapon represents being unarmed.
 */
trait Weapon extends PIWeapon {

  /**
   * Sets the owner of the weapon.
   *
   * @param newOwner The new owner of the weapon.
   */
  def owner_=(newOwner: Option[Character]): Unit

  /** The magical attack points of the weapon. For MagicWeapons will return an
   * Int, for common weapon will throw an Exception, common weapons should not be able to have magic properties */
  def magicAttack: Int

}

