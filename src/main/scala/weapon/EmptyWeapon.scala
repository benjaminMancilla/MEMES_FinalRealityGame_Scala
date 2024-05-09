package weapon

import entity.character.{BlackMage, Character, Ninja, Paladin, Warrior, WhiteMage}

class EmptyWeapon(val _name: String = "Empty",
                  val _attackPoints: Int = 0,
                  val _weight: Int = 0,
                  var _owner: Option[Character]) extends AbstractWeapon {

  def canBeEquipped(character: Warrior): Boolean = true
  def canBeEquipped(character: Paladin): Boolean = true
  def canBeEquipped(character: Ninja): Boolean = true
  def canBeEquipped(character: WhiteMage): Boolean = true
  def canBeEquipped(character: BlackMage): Boolean = true

  override def isEmpty: Boolean = true

}
