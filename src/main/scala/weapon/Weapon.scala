package weapon
import character.Character

trait Weapon {
  val _name: String
  val _attackPoints: Int
  val _weight: Int
  var _owner: Character

  def name: String
  def attackPoints: Int
  def weight: Int
  def owner: Character
  def owner_=(newOwner: Character): Unit


}
