package weapon

import entity.character.{BlackMage, Character, Ninja, Paladin, Warrior, WhiteMage}

class Wand (val _name: String,
            val _attackPoints: Int,
            val _weight: Int,
            var _owner: Option[Character],
            val _magicAttack: Int) extends AbstractMagicWeapon {

  def canBeEquipped(character: Warrior): Boolean = false
  def canBeEquipped(character: Paladin): Boolean = false
  def canBeEquipped(character: Ninja): Boolean = true
  def canBeEquipped(character: WhiteMage): Boolean = true
  def canBeEquipped(character: BlackMage): Boolean = true
}
