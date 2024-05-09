package weapon

import entity.character.Character

//Usar Option para owner
abstract class AbstractWeapon extends Weapon {

  def name: String = _name
  def attackPoints: Int = _attackPoints
  def weight: Int = _weight

  def owner: Option[Character] = _owner
  def owner_=(newOwner: Option[Character]): Unit = {
    _owner = newOwner
  }

  def isEmpty: Boolean = false


}
