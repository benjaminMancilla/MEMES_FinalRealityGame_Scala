package weapon

import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

class Bow (nameI: String, attackPointsI: Int, weightI: Int)
  extends AbstractWeapon(nameI: String, attackPointsI: Int, weightI: Int){

  def canBeEquipped(character: Warrior): Boolean = true
  def canBeEquipped(character: Paladin): Boolean = false
  def canBeEquipped(character: Ninja): Boolean = true
  def canBeEquipped(character: WhiteMage): Boolean = true
  def canBeEquipped(character: BlackMage): Boolean = false

}
