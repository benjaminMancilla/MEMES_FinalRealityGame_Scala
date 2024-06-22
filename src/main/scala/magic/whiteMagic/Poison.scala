package magic.whiteMagic

import effect.Poisoned
import entity.Entity
import entity.character.magicCharacter.MagicCharacter
import magic.OffensiveSpell

/**
 * Represents the Poison spell in the game.
 *
 * The `Poison` class extends the `AbstractWhiteMagic` class and implements the `OffensiveSpell` trait.
 * It defines the characteristics and behavior of the Poison spell, including its name, mana cost,
 * and the application of its effect to the target.
 */
class Poison extends AbstractWhiteMagic with OffensiveSpell {
  /** The name of the Poison spell. */
  val _name: String = "Poison"
  /** The mana cost of the Poison spell. */
  val _manaCost: Int = 30

  /**
   * Applies the Poison spell's effect to the target.
   *
   * The effect of the Poison spell is to apply the Poisoned effect to the target.
   * However, the implementation for this effect is not yet implemented, so this method
   * currently prints a placeholder message for testing purposes.
   *
   * @param sorcerer The magicE character casting the spell.
   * @param target The entityE that the spell is being cast on.
   * @param mDmg The magicE damage (unused parameter, as Poison is not a damaging spell).
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    target.addEffect(new Poisoned(magicDamageI = mDmg))
    println("Poisoned") // Placeholder for momentary tests
  }

  /**
   * Checks if the target is valid for the Poison spell.
   *
   * This method ensures that the target can be affected by the Poison spell.
   *
   * @param target The entityE that the spell is being cast on.
   */
  override def checkTarget(target: Entity): Unit = { target.checkSpell(this) }

}

