package magic

import exceptions.NonMagicWeaponException
import weapon.{MagicWeapon, Weapon}
import entity.Entity
import entity.character.MagicCharacter

trait Magic {
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit
  def name: String
  def manaCost: Int

}
