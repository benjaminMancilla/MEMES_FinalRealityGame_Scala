package entity.character

import entity.{AbstractEntity, Entity}
import weapon.{EmptyWeapon, Weapon}

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
  private var _equipped_weapon: Weapon = new EmptyWeapon()

  /**
   * Gets the currently equipped weapon of the character.
   *
   * @return The currently equipped weapon.
   */
  def equipped_weapon: Weapon = _equipped_weapon

  /**
   * Sets the currently equipped weapon of the character.
   *
   * @param new_weapon The new weapon to be equipped.
   */
  def equipped_weapon_=(new_weapon: Weapon): Unit = {
    _equipped_weapon = new_weapon
  }

  /**
   * Calculates the value for the character's status bar.
   *
   * @return The value for the character's status bar.
   */
  def barValue: Int = {
    weight + equipped_weapon.weight / 2
  }

  /**
   * Changes the equipped weapon of the character.
   *
   * @param newWeapon The new weapon to be equipped.
   */
  def changeWeapon(newWeapon: Weapon): Unit = {
    if (checkValidWeapon(newWeapon: Weapon)) {
      if (newWeapon.owner.isDefined) {
        println(s"${newWeapon.name} already has an owner.")
        return
      }
      equipped_weapon.owner = None
      equipped_weapon = newWeapon
      newWeapon.owner = Some(this)
    } else {
      println(s"Cannot equip ${newWeapon.name} on this character.")
    }
  }

  /**
   * Unequips the currently equipped weapon of the character.
   */
  def unequipWeapon(): Unit = {
    equipped_weapon.owner = None
    val emptyW = new EmptyWeapon()
    _equipped_weapon = emptyW
    emptyW.owner = Some(this)
  }

  /**
   * Checks if a weapon can be equipped by the character.
   *
   * @param newWeapon The new weapon to be equipped.
   * @return true if the weapon can be equipped, false otherwise.
   */
  protected def checkValidWeapon(newWeapon: Weapon): Boolean

  /**
   * Performs an attack on another entity.
   *
   * @param entity The entity being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  override def doAttack(entity: Entity, damage: Int): Unit = {
    if (!equipped_weapon.isEmptyWeapon) {
      doDamage(entity: Entity, damage: Int)
    }
    else {
      printf(s"$name does not have an equipped weapon and cannot attack.")
    }
  }

  /**
   * Gets the type name of the character.
   *
   * @return The type name of the character.
   */
  def typeName: String = _typeName
}


