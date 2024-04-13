package weapon
import character.Character

abstract class AbstractWeapon extends Weapon {

  def name: String = _name
  def attackPoints: Int = _attackPoints
  def weight: Int = _weight
  def owner: Character = _owner
  def owner_=(newOwner: Character): Unit = {
    _owner = newOwner
  }


}
