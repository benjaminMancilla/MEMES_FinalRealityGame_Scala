package magic

import entity.Entity
import entity.character.Character
import entity.character.{BlackMage, MagicCharacter, WhiteMage}
import entity.enemy.Enemy
import exceptions.{InvalidMagicType, InvalidSpellTarget, NonMagicWeaponException}
import weapon.{MagicWeapon, Weapon}

/**
 * Represents an abstract magic spell in the game.
 *
 * The `AbstractMagic` class extends the `Magic` trait and provides common functionality for all magic spells.
 * It defines properties like name and mana cost, and implements methods for checking compatibility with sorcerers.
 */
abstract class AbstractMagic extends Magic {

  /**
   * The name of the magic spell.
   */
  val _name: String

  /**
   * The mana cost of the magic spell.
   */
  val _manaCost: Int

  /**
   * Returns the name of the magic spell.
   *
   * @return The name of the magic spell.
   */
  def name: String = _name

  /**
   * Returns the mana cost of the magic spell.
   *
   * @return The mana cost of the magic spell.
   */
  def manaCost: Int = _manaCost

  /**
   * Checks if a BlackMage can cast the spell.
   *
   * This method throws an exception indicating that the spell is not compatible with black magic.
   *
   * @param sorcerer The BlackMage attempting to cast the spell.
   * @throws InvalidMagicType if the spell is not compatible with black magic.
   */
  def checkSorcerer(sorcerer: BlackMage): Unit = {
    throw new InvalidMagicType(s"${this.name} spell is not compatible with ${sorcerer.name} black magic")
  }

  /**
   * Checks if a WhiteMage can cast the spell.
   *
   * This method throws an exception indicating that the spell is not compatible with white magic.
   *
   * @param sorcerer The WhiteMage attempting to cast the spell.
   * @throws InvalidMagicType if the spell is not compatible with white magic.
   */
  def checkSorcerer(sorcerer: WhiteMage): Unit = {
    throw new InvalidMagicType(s"${this.name} spell is not compatible with ${sorcerer.name} white magic")
  }

}

