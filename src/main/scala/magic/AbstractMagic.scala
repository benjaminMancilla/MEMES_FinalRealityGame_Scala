package magic

import entity.Entity
import entity.character.Character
import entity.character.{BlackMage, MagicCharacter, WhiteMage}
import entity.enemy.Enemy
import exceptions.{InvalidMagicType, InvalidSpellTarget, NonMagicWeaponException}
import weapon.{MagicWeapon, Weapon}

abstract class AbstractMagic extends Magic{

  val _name: String
  val _manaCost: Int

  def name: String = _name

  def manaCost: Int = _manaCost

  def checkSorcerer(sorcerer:BlackMage): Unit = {
    throw new InvalidMagicType(s"${this.name} spell is not compatible with ${sorcerer.name} black magic")
  }

  def checkSorcerer(sorcerer:WhiteMage): Unit = {
    throw new InvalidMagicType(s"${this.name} spell is not compatible with ${sorcerer.name} white magic")
  }



}
