package magic

import entity.Entity
import entity.character.MagicCharacter
import entity.enemy.Enemy

import scala.util.Random

class Poison extends AbstractWhiteMagic with OffensiveSpell {
  val _name: String = "Poison"
  val _manaCost: Int = 30

  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    //Apply Poison effect to target
    println("Poison") //Just for momentary tests

  }

  override def checkTarget(target:Entity): Unit = {target.checkSpell(this)}

}
