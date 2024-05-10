package weapon

import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

class Bow (name: String, attackPoints: Int, weight: Int)
  extends AbstractWeapon(name: String, attackPoints: Int, weight: Int){

  def canBeEquipped(character: Warrior): Boolean = true
  def canBeEquipped(character: Paladin): Boolean = false
  def canBeEquipped(character: Ninja): Boolean = true
  def canBeEquipped(character: WhiteMage): Boolean = true
  def canBeEquipped(character: BlackMage): Boolean = false

}
