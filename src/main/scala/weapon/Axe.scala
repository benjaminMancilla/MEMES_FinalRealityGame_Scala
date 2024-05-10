package weapon

import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

class Axe (name: String, attackPoints: Int, weight: Int)
  extends AbstractWeapon(name: String, attackPoints: Int, weight: Int) {

  def canBeEquipped(character: Warrior): Boolean = true
  def canBeEquipped(character: Paladin): Boolean = true
  def canBeEquipped(character: Ninja): Boolean = false
  def canBeEquipped(character: WhiteMage): Boolean = false
  def canBeEquipped(character: BlackMage): Boolean = false

}
