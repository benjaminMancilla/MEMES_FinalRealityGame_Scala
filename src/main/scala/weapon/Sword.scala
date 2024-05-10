package weapon

import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

class Sword (nameI: String, attackPointsI: Int, weightI: Int)
  extends AbstractWeapon(nameI: String, attackPointsI: Int, weightI: Int) {

  def canBeEquipped(character: Warrior): Boolean = true
  def canBeEquipped(character: Paladin): Boolean = true
  def canBeEquipped(character: Ninja): Boolean = true
  def canBeEquipped(character: WhiteMage): Boolean = false
  def canBeEquipped(character: BlackMage): Boolean = true

}
