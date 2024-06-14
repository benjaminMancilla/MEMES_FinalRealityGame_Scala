package weapon

import entity.character.Character

/**
 * Trait representing a specific weaponE.
 * Weapons are equipment that characters can use in the game.
 * Compatibility with character types determines whether a weaponE can be equipped by a specific character.
 * Weapons can either be ordinary or magical.
 * Magical weapons can be used to cast spells.
 * Each weaponE can only be equipped by one character at a time.
 * None in equipped weaponE represents being unarmed.
 */
trait Weapon extends PIWeapon {

  /**
   * Sets the owner of the weaponE.
   *
   * @param newOwner The new owner of the weaponE.
   */
  def owner_=(newOwner: Option[Character]): Unit

  /** The magical attack points of the weaponE. For MagicWeapons will return an
   * Int, for common weaponE will throw an Exception, common weapons should not be able to have magicE properties */
  def magicAttack: Int

}

