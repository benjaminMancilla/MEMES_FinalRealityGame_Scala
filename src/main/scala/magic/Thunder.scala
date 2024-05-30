package magic

import entity.Entity
import entity.character.MagicCharacter
import entity.enemy.Enemy

import scala.util.Random

class Thunder extends AbstractBlackMagic with OffensiveSpell {
  val _name: String = "Thunder"
  val _manaCost: Int = 20

  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    sorcerer.doAttack(target, mDmg)
    val random = new Random()
    val randomResult = random.nextDouble()
    if (randomResult <= 0.3) {
      //We apply the Paralyzed effect to target
      println("Paralyzed") //Just for momentary tests

    }

  }

  override def checkTarget(target:Entity): Unit = {target.checkSpell(this)}

}
