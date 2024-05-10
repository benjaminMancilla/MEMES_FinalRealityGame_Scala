package weapon

import entity.character.Character

abstract class AbstractWeapon(nameI: String, attackPointsI: Int, weightI: Int) extends Weapon {
  protected val _name: String = try {
    if (nameI.nonEmpty) nameI else throw new IllegalArgumentException("Name of Weapon can not be Empty.")
  } catch {
    case _: IllegalArgumentException => ""
  }
  private val _attackPoints: Int = try {
    if (attackPointsI>=0) attackPointsI else throw new IllegalArgumentException("Weapon power attack can not be negative.")
  } catch {
    case _: IllegalArgumentException => 0
  }
  protected val _weight: Int = try {
    if (weightI>=0) weightI else throw new IllegalArgumentException("Weapon weight can not be negative.")
  } catch {
    case _: IllegalArgumentException => 0
  }
  protected var _owner: Option[Character] = None
  private var _hasOwner: Boolean = false

  def name: String = _name
  def attackPoints: Int = _attackPoints
  def weight: Int = _weight

  def owner: Option[Character] = _owner
  def owner_=(newOwner: Option[Character]): Unit = {
    _owner = newOwner

  }

  def hasOwner: Boolean = _hasOwner

  def setHasOwner(newBool: Boolean): Unit = {
    _hasOwner = newBool
  }

  def isEmptyWeapon: Boolean = false

}
