package weapon

import entity.character.Character
import exceptions.{InvalidNameException, InvalidStatException, Require}

/**
 * Abstract class representing a specific type of weapon.
 * Weapons are equipment that characters can use in the game.
 * This class provides common functionality and properties for weapons.
 * Compatibility with character types determines whether a weapon can be equipped by a specific character.
 * Weapons can either be ordinary or magical.
 * Magical weapons can be used to cast spells.
 * Each weapon can only be equipped by one character at a time.
 * EmptyWeapon represents being unarmed.
 *
 * @param nameI         The name of the weapon.
 * @param attackPointsI The attack points of the weapon.
 * @param weightI       The weight of the weapon.
 */
abstract class AbstractWeapon(nameI: String, attackPointsI: Int, weightI: Int) extends Weapon {

  /** The name of the weapon. */
  protected val _name: String = try {Require.Name(nameI) lengthAtLeast 3}
  catch {
    case _: InvalidNameException => "Weapon"
  }

  /** The attack points of the weapon. */
  private val _attackPoints: Int = try {Require.Stat(attackPointsI, "attackPoints") in (0 to 1000)}
  catch {
    case _: InvalidStatException => 10
  }

  /** The weight of the weapon. */
  protected val _weight: Int = try {Require.Stat(weightI, "weight") in (0 to 1000)}
  catch {
    case _: InvalidStatException => 10
  }

  /** The owner of the weapon. */
  private  var _owner: Option[Character] = None

  /** Indicates whether the weapon has an owner. */
  private var _hasOwner: Boolean = false

  /** Retrieves the name of the weapon. */
  def name: String = _name

  /** Retrieves the attack points of the weapon. */
  def attackPoints: Int = _attackPoints

  /** Retrieves the weight of the weapon. */
  def weight: Int = _weight

  /** Retrieves the owner of the weapon. */
  def owner: Option[Character] = _owner

  /** Sets the owner of the weapon. */
  def owner_=(newOwner: Option[Character]): Unit = {
    _owner = newOwner
  }

  /** Indicates whether the weapon has an owner. */
  def hasOwner: Boolean = _hasOwner

  /** Sets whether the weapon has an owner. */
  def setHasOwner(newBool: Boolean): Unit = {
    _hasOwner = newBool
  }

  /** Indicates whether the weapon is empty (unarmed). */
  def isEmptyWeapon: Boolean = false

}

