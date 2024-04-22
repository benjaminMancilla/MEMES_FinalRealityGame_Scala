package entity.character

import entity.AbstractEntity
import weapon.Weapon
abstract class AbstractCharacter extends AbstractEntity with Character {

  val _isPlayer: Boolean = true

  var _equipped_weapon: Weapon
  val _type_name: String = "Invalid Character"
  val _weapon_list: List[String] = List("Not available weapons")


  def type_name: String = _type_name
  def weapon_list: List[String] = _weapon_list
  def equipped_weapon: Weapon = _equipped_weapon
  def equipped_weapon_=(new_weapon: Weapon): Unit = {
    _equipped_weapon = new_weapon
  }

  def barValue: Int = {
    weight + equipped_weapon.weight/2
  }

  def changeWeapon(new_weapon: Weapon): Unit = {
    if (_weapon_list.contains(new_weapon._weapon_type)) {
      _equipped_weapon = new_weapon
    } else {}
  }

}

