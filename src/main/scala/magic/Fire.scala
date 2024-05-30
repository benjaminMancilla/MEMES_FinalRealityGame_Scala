package magic
import entity.Entity
import entity.character.{BlackMage, MagicCharacter}
import entity.enemy.Enemy

import scala.util.Random

class Fire extends AbstractBlackMagic with OffensiveSpell {
  val _name: String = "Fire"
  val _manaCost: Int = 15

  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    sorcerer.doAttack(target, mDmg)
    val random = new Random()
    val randomResult = random.nextDouble()
    if (randomResult <= 0.2) {
      //We apply the Burn effect to target
      println("Burn") //Just for momentary tests

    }

  }

  override def checkTarget(target:Entity): Unit = {target.checkSpell(this)}

}
