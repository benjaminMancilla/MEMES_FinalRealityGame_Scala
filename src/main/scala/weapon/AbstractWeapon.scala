package weapon

import entity.character.Character

abstract class AbstractWeapon(name: String, attackPoints: Int, weight: Int) extends Weapon {
  protected val _name: String = name
  private val _attackPoints: Int = attackPoints
  protected val _weight: Int = weight
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
