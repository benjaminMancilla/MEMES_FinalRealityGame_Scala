package magic

import entity.Entity
import entity.character.MagicCharacter

import scala.util.Random

class Heal extends AbstractWhiteMagic with DefensiveSpell {
  val _name: String = "Heal"
  val _manaCost: Int = 15

  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    val heal = (target.hit_points/10) * 3
    sorcerer.doHeal(target, heal)

  }

}
