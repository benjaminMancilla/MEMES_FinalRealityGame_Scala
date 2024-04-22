package weapon

import entity.character.Character

abstract class AbstractWeapon extends Weapon {

  def name: String = _name
  def attackPoints: Int = _attackPoints
  def weight: Int = _weight
  def owner: Character = _owner
  def weapon_type: String = _weapon_type
  def owner_=(newOwner: Character): Unit = {
    _owner = newOwner
  }


}
