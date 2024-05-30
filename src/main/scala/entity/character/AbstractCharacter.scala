package entity.character

import entity.enemy.ConcreteEnemy
import entity.{AbstractEntity, Entity}
import exceptions.{EmptyWeaponException, InvalidSpellTarget, ProhibitedTarget, Require}
import magic.DefensiveSpell
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
   * Checks with the Require if the new weapon is valid, for that, it needs to do not
   * have already an owner, plus it has to be a compatible weapon with the character.
   *
   * If the check is cleared, the actual weapon in unequipped (now it has no owner)
   * then the new weapon is the equipped weapon and the owner is updated.
   *
   * @param newWeapon The new weapon to be equipped.
   */
  private def setWeapon(newWeapon: Option[Weapon]): Unit = {
    Require.WeaponAssignment(newWeapon, this) validWeapon (newWeapon, this)

    equipped_weapon.foreach(_.owner = None)
    equipped_weapon = newWeapon
    newWeapon.foreach(_.owner = Some(this))
  }

  /**
   * Public method of setWeapon
   * @param newWeapon The new weapon to be equipped.
   */
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
   * Performs an attack on another enemy.
   *
   * @param entity The enemy being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  def doAttack(entity: ConcreteEnemy, damage: Int): Unit = {
    if (equipped_weapon.isDefined) {
      doDamage(entity: Entity, damage: Int)
    }
    else {
      throw new EmptyWeaponException("Characters can only attack when they have a weapon")
    }
  }

  /**
   * If the attack is performed on a Character, we get an exception.
   *
   * @param entity The enemy being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  def doAttack(entity: Character, damage: Int): Unit = {
    throw new ProhibitedTarget("Characters can not attack other characters")
  }

  /**
   * Gets the type name of the character.
   *
   * @return The type name of the character.
   */
  def typeName: String = _typeName



  /**
   * Characters can only receive defensive spells
   *
   * @return Unit
   */
  override def checkSpell(spell: DefensiveSpell): Unit = {}

}


