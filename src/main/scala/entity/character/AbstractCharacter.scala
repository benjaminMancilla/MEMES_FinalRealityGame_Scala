package entity.character

import entity.enemy.ConcreteEnemy
import entity.{AbstractEntity, Entity}
import exceptions.{ProhibitedTarget, Require}
import weapon.Weapon

/**
 * Abstract class representing a character entity in a game with basic properties and behavior.
 * This class extends the AbstractEntity class and implements the Character trait.
 *
 * @param nameI       The name of the character.
 * @param hit_pointsI The initial hit points of the character.
 * @param defenseI    The defense level of the character.
 * @param weightI     The weight of the character.
 */
abstract class AbstractCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractEntity(nameI, hit_pointsI, defenseI, weightI) with Character  {

  /**
   * The type name of the character.
   */
  protected val _typeName: String

  /**
   * Indicates whether the character is controlled by the player.
   */
  protected val _isPlayer: Boolean = true

  /**
   * The currently equipped weapon of the character.
   */
  private var _equipped_weapon: Option[Weapon] = None

  /**
   * Gets the currently equipped weapon of the character.
   *
   * @return The currently equipped weapon.
   */
  def equipped_weapon: Option[Weapon] = _equipped_weapon

  /**
   * Sets the currently equipped weapon of the character.
   *
   * @param new_weapon The new weapon to be equipped.
   */
  def equipped_weapon_=(new_weapon: Option[Weapon]): Unit = {
    _equipped_weapon = new_weapon
  }

  /**
   * Calculates the value for the character's status bar.
   *
   * @return The value for the character's status bar.
   */
  def barValue: Int = {
    val weaponWeight = _equipped_weapon.map(_.weight).getOrElse(0)
    weight + weaponWeight / 2
  }

  /**
   * Changes the equipped weapon of the character.
   *
   * @param newWeapon The new weapon to be equipped.
   */
  private def setWeapon(newWeapon: Option[Weapon]): Unit = {
    Require.WeaponAssigment(newWeapon, this) validWeapon (newWeapon, this)

    equipped_weapon.foreach(_.owner = None)
    equipped_weapon = newWeapon
    newWeapon.foreach(_.owner = Some(this))
  }

  def changeWeapon(newWeapon: Option[Weapon]): Unit = {
    setWeapon(newWeapon)

  }

  /**
   * Unequips the currently equipped weapon of the character.
   */
  def unequipWeapon(): Unit = {
    equipped_weapon.foreach(_.owner = None)
    equipped_weapon = None
  }

  /**
   * Checks if a weapon can be equipped by the character.
   *
   * @param newWeapon The new weapon to be equipped.
   * @return true if the weapon can be equipped, false otherwise.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean

  /**
   * Performs an attack on another entity.
   *
   * @param entity The entity being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  def doAttack(entity: ConcreteEnemy, damage: Int): Unit = {
    if (equipped_weapon.isDefined) {
      doDamage(entity: Entity, damage: Int)
    }
    else {
      printf(s"$name does not have an equipped weapon and cannot attack.")
    }
  }

  def doAttack(entity: Character, damage: Int): Unit = {
    throw new ProhibitedTarget("Characters can not attack other characters")
  }

  /**
   * Gets the type name of the character.
   *
   * @return The type name of the character.
   */
  def typeName: String = _typeName

}


