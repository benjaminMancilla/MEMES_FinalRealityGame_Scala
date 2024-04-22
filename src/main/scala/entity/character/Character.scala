package entity.character

import entity.Entity
import weapon.Weapon

trait Character extends Entity{
  var _equipped_weapon: Weapon
  val _type_name: String
  val _weapon_list: List[String]

  def changeWeapon(new_weapon : Weapon): Unit

  def type_name: String
  def weapon_list: List[String]
  def equipped_weapon: Weapon
  def equipped_weapon_=(new_weapon: Weapon): Unit

}


