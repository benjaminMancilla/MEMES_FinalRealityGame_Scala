package model.entity.character

import exceptions.weaponE.EmptyWeaponException
import exceptions.Require
import exceptions.entityE.ProhibitedTarget
import model.entity.{AbstractEntity, Entity}
import model.entity.enemy.ConcreteEnemy
import model.magic.DefensiveSpell
import model.weapon.Weapon

import java.lang

/**
 * Abstract class representing a character entityE in a game with basic properties and behavior.
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
   * The currently equipped weaponE of the character.
   */
  private var _equipped_weapon: Option[Weapon] = None

  /**
   * Gets the currently equipped weaponE of the character.
   *
   * @return The currently equipped weaponE.
   */
  def equipped_weapon: Option[Weapon] = _equipped_weapon

  /**
   * Sets the currently equipped weaponE of the character.
   *
   * @param new_weapon The new weaponE to be equipped.
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
   * Changes the equipped weaponE of the character.
   * Checks with the Require if the new weaponE is valid, for that, it needs to do not
   * have already an owner, plus it has to be a compatible weaponE with the character.
   *
   * If the check is cleared, the actual weaponE in unequipped (now it has no owner)
   * then the new weaponE is the equipped weaponE and the owner is updated.
   *
   * @param newWeapon The new weaponE to be equipped.
   */
  private def setWeapon(newWeapon: Option[Weapon]): Unit = {
    Require.WeaponAssignment(newWeapon, this) validWeapon (newWeapon, this)

    equipped_weapon.foreach(_.owner = None)
    equipped_weapon = newWeapon
    newWeapon.foreach(_.owner = Some(this))
  }

  /**
   * Public method of setWeapon
   * @param newWeapon The new weaponE to be equipped.
   */
  override def changeWeapon(newWeapon: Option[Weapon]): Unit = {
    setWeapon(newWeapon)

  }

  /**
   * Unequips the currently equipped weaponE of the character.
   */
  def unequipWeapon(): Unit = {
    equipped_weapon.foreach(_.owner = None)
    equipped_weapon = None
  }

  /**
   * Checks if a weaponE can be equipped by the character.
   *
   * @param newWeapon The new weaponE to be equipped.
   * @return true if the weaponE can be equipped, false otherwise.
   */
  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean

  /**
   * Performs an attack on another enemy.
   *
   * @param entity The enemy being attacked.
   */
  def doAttack(entity: ConcreteEnemy): Unit = {
    if (equipped_weapon.isDefined) {
      val damage: Int = equipped_weapon.map(_.attackPoints).getOrElse(0)
      doDamage(entity: Entity, damage: Int)
    }
    else {
      throw new EmptyWeaponException("Characters can only attack when they have a weaponE")
    }
  }

  /**
   * If the attack is performed on a Character, we get an exception.
   *
   * @param entity The enemy being attacked.
   */
  def doAttack(entity: Character): Unit = {
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


  /**
   * Double Dispatch for attacks, gives the class
   * to the attacker so it can know if is a legal
   * attack or not.
   *
   * @return Unit
   */
  def receiveAttack(entity: Entity): Unit = {
    entity.doAttack(this)
  }
}


