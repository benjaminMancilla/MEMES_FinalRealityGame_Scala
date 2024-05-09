package weapon

import entity.character.{BlackMage, Character, Ninja, Paladin, Warrior, WhiteMage}

class Sword (val _name: String,
             val _attackPoints: Int,
             val _weight: Int,
             var _owner: Option[Character]) extends AbstractWeapon {

  def canBeEquipped(character: Warrior): Boolean = true
  def canBeEquipped(character: Paladin): Boolean = true
  def canBeEquipped(character: Ninja): Boolean = true
  def canBeEquipped(character: WhiteMage): Boolean = false
  def canBeEquipped(character: BlackMage): Boolean = true

}