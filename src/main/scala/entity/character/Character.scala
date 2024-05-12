package entity.character

import entity.Entity
import weapon.Weapon

trait Character extends Entity with PICharacter {

  def changeWeapon(new_weapon : Weapon): Unit
  def unequipWeapon(): Unit

  def equipped_weapon: Weapon
  def equipped_weapon_=(new_weapon: Weapon): Unit

}


