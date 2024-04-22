package weapon

import entity.character.Character

trait Weapon {
  val _name: String
  val _attackPoints: Int
  val _weight: Int
  var _owner: Character
  val _weapon_type: String
  def name: String
  def attackPoints: Int
  def weight: Int
  def owner: Character
  def weapon_type: String
  def owner_=(newOwner: Character): Unit



}
