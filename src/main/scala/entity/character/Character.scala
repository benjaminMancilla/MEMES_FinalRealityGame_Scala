package entity.character

import entity.Entity
import weapon.Weapon

//Usar Object Initialization pdf 07
trait Character extends Entity with PICharacter {
  var _equipped_weapon: Weapon

  def changeWeapon(new_weapon : Weapon): Unit

  def equipped_weapon: Weapon
  def equipped_weapon_=(new_weapon: Weapon): Unit

}


