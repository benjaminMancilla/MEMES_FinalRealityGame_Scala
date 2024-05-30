package magic

import entity.Entity
import entity.character.MagicCharacter
import entity.enemy.Enemy

import scala.util.Random

class Paralyze extends AbstractWhiteMagic with OffensiveSpell {
  val _name: String = "Paralyze"
  val _manaCost: Int = 25

  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    //Apply Paralyzed effect to target
    println("Paralyzed") //Just for momentary tests

  }

  override def checkTarget(target:Entity): Unit = {target.checkSpell(this)}

}
