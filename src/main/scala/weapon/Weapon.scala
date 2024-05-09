package weapon

import entity.character.{BlackMage, Character, Ninja, Paladin, Warrior, WhiteMage}
//No estoy seguro de que se puede declarar privacidad aca
trait Weapon extends PIWeapon {
  protected val _name: String
  protected val _attackPoints: Int
  protected val _weight: Int
  protected var _owner: Option[Character]
  def owner_=(newOwner: Option[Character]): Unit



}
