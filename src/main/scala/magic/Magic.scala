package magic

import entity.Entity
import entity.character.{BlackMage, Character, MagicCharacter, WhiteMage}
import entity.enemy.Enemy

trait Magic {
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit
  def name: String
  def manaCost: Int

  def checkSorcerer(sorcerer:BlackMage): Unit
  def checkSorcerer(sorcerer:WhiteMage): Unit
  def checkTarget(target:Entity): Unit

}
