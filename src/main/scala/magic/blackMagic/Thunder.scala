package magic.blackMagic

import effect.Paralyzed
import entity.Entity
import entity.character.magicCharacter.MagicCharacter
import magic.OffensiveSpell

import scala.util.Random

/**
 * Represents the Thunder spell in the game.
 *
 * The `Thunder` class extends the `AbstractBlackMagic` class and implements the `OffensiveSpell` trait.
 * It defines the characteristics and behavior of the Thunder spell, including its name, mana cost,
 * and the application of its effect to the target.
 */
class Thunder extends AbstractBlackMagic with OffensiveSpell {
  /** The name of the Thunder spell. */
  val _name: String = "Thunder"
  /** The mana cost of the Thunder spell. */
  val _manaCost: Int = 20

  /**
   * Applies the Thunder spell's effect to the target.
   *
   * The effect of the Thunder spell is to inflict damage on the target. Additionally, there is a 30%
   * chance to apply the Paralyzed effect, although the implementation for this effect is not yet implemented.
   *
   * @param sorcerer The magicE character casting the spell.
   * @param target The entityE that the spell is being cast on.
   * @param mDmg The magicE damage inflicted by the spell.
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    sorcerer.doDamage(target, mDmg)
    val random = new Random()
    val randomResult = random.nextDouble()
    if (randomResult <= 0.3) {
      target.addEffect(new Paralyzed)
      println("Paralyzed") // Placeholder for momentary tests
    }
  }

  /**
   * Checks if the target is valid for the Thunder spell.
   *
   * This method ensures that the target can be affected by the Thunder spell.
   *
   * @param target The entityE that the spell is being cast on.
   */
  override def checkTarget(target: Entity): Unit = { target.checkSpell(this) }

}

