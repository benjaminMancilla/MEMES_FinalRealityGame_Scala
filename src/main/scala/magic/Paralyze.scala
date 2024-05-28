package magic

import entity.Entity
import entity.character.MagicCharacter

import scala.util.Random

class Paralyze extends AbstractWhiteMagic with OffensiveSpell {
  val _name: String = "Paralyze"
  val _manaCost: Int = 25

  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    //Apply Froze effect to target

  }

}
