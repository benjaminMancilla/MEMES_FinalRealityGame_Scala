package character
import weapon.Weapon

trait Character {
  val _name: String
  val _hit_points: Int
  val _defense: Int
  val _weight: Int
  var _equipped_weapon: Weapon

  var _current_hit_points: Int
  var _characterState: Boolean


  def changeWeapon(new_weapon : Weapon): Unit

  def name: String
  def hitPoints: Int
  def defense: Int
  def weight: Int
  def equippedWeapon: Weapon
  def equippedWeapon_=(newWeapon: Weapon): Unit
  def currentHitPoints: Int
  def currentHitPoints_=(newCurrentHitPoints: Int): Unit
  def characterState: Boolean
  def characterState_=(newCharacterState: Boolean): Unit
}


