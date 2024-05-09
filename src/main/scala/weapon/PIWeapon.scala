package weapon

import entity.character.{BlackMage, Character, Ninja, Paladin, Warrior, WhiteMage}

trait PIWeapon {
  def name: String
  def attackPoints: Int
  def weight: Int
  def owner: Option[Character]
  def canBeEquipped(character: Warrior): Boolean
  def canBeEquipped(character: Paladin): Boolean
  def canBeEquipped(character: Ninja): Boolean
  def canBeEquipped(character: WhiteMage): Boolean
  def canBeEquipped(character: BlackMage): Boolean
  def isEmpty: Boolean

}
