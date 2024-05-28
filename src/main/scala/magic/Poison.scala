package magic

import entity.Entity
import entity.character.MagicCharacter

import scala.util.Random

class Poison extends AbstractWhiteMagic with OffensiveSpell {
  val _name: String = "Poison"
  val _manaCost: Int = 30

  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    //Apply Poison effect to target

  }

}
