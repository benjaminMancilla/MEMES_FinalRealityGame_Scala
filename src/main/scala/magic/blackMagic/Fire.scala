package magic.blackMagic

import effect.Burned
import entity.Entity
import entity.character.magicCharacter.MagicCharacter
import magic.OffensiveSpell

import scala.util.Random

/**
 * Represents the Fire spell in the game.
 *
 * The `Fire` class extends the `AbstractBlackMagic` class and implements the `OffensiveSpell` trait.
 * It defines the characteristics and behavior of the Fire spell, including its name, mana cost,
 * and the application of its effect to the target.
 */
class Fire extends AbstractBlackMagic with OffensiveSpell {
  /** The name of the Fire spell. */
  val _name: String = "Fire"
  /** The mana cost of the Fire spell. */
  val _manaCost: Int = 15

  /**
   * Applies the Fire spell's effect to the target.
   *
   * The effect of the Fire spell is to inflict damage on the target. Additionally, there is a 20% chance
   * to apply the Burn effect, although the implementation for this effect is not yet implemented.
   *
   * @param sorcerer The magicE character casting the spell.
   * @param target The entityE that the spell is being cast on.
   * @param mDmg The magicE damage inflicted by the spell.
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    sorcerer.doDamage(target, mDmg)
    val random = new Random()
    val randomResult = random.nextDouble()
    if (randomResult <= 0.2) {
      target.addEffect(new Burned(magicDamageI = mDmg))
      println("Burn") // Placeholder for momentary tests
    }
  }

  /**
   * Checks if the target is valid for the Fire spell.
   *
   * This method ensures that the target can be affected by the Fire spell.
   *
   * @param target The entityE that the spell is being cast on.
   */
  override def checkTarget(target: Entity): Unit = {target.checkSpell(this)}

}

