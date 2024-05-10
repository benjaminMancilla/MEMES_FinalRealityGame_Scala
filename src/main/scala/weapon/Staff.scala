package weapon

import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}

class Staff (name: String, attackPoints: Int, weight: Int, magicAttack: Int)
  extends AbstractMagicWeapon(name: String, attackPoints: Int, weight: Int, magicAttack: Int) {

  def canBeEquipped(character: Warrior): Boolean = false
  def canBeEquipped(character: Paladin): Boolean = false
  def canBeEquipped(character: Ninja): Boolean = false
  def canBeEquipped(character: WhiteMage): Boolean = true
  def canBeEquipped(character: BlackMage): Boolean = true

}
