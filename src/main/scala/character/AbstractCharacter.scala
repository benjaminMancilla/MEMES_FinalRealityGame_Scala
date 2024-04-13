package character
import weapon.Weapon
abstract class AbstractCharacter extends Character {

  val _name: String
  val _hit_points: Int
  val _defense: Int
  val _weight: Int
  var _equipped_weapon: Weapon


  var _current_hit_points: Int = _hit_points
  var _characterState: Boolean = true


  def name: String = _name
  def hitPoints: Int = _hit_points
  def defense: Int = _defense
  def weight: Int = _weight
  def equippedWeapon: Weapon = _equipped_weapon
  def equippedWeapon_=(newWeapon: Weapon): Unit = {
    _equipped_weapon = newWeapon
  }
  def currentHitPoints: Int = _current_hit_points
  def currentHitPoints_=(newCurrentHitPoints: Int): Unit = {
    _current_hit_points = newCurrentHitPoints
  }

  def characterState: Boolean = _characterState

  def characterState_=(newCharacterState: Boolean): Unit = {
    _characterState = newCharacterState
  }
  def changeWeapon(new_weapon: Weapon): Unit = {
    // falta lo de la tabla
    equippedWeapon = new_weapon
  }
}

