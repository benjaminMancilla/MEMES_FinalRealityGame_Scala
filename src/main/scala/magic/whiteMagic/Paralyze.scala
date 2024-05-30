package magic.whiteMagic

import entity.Entity
import entity.character.magicCharacter.MagicCharacter
import magic.OffensiveSpell

/**
 * Represents the Paralyze spell in the game.
 *
 * The `Paralyze` class extends the `AbstractWhiteMagic` class and implements the `OffensiveSpell` trait.
 * It defines the characteristics and behavior of the Paralyze spell, including its name, mana cost,
 * and the application of its effect to the target.
 */
class Paralyze extends AbstractWhiteMagic with OffensiveSpell {
  /** The name of the Paralyze spell. */
  val _name: String = "Paralyze"
  /** The mana cost of the Paralyze spell. */
  val _manaCost: Int = 25

  /**
   * Applies the Paralyze spell's effect to the target.
   *
   * The effect of the Paralyze spell is to apply the Paralyzed effect to the target.
   * However, the implementation for this effect is not yet implemented, so this method
   * currently prints a placeholder message for testing purposes.
   *
   * @param sorcerer The magic character casting the spell.
   * @param target The entity that the spell is being cast on.
   * @param mDmg The magic damage (unused parameter, as Paralyze is not a damaging spell).
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    // Apply the Paralyzed effect to the target (not implemented yet)
    println("Paralyzed") // Placeholder for momentary tests
  }

  /**
   * Checks if the target is valid for the Paralyze spell.
   *
   * This method ensures that the target can be affected by the Paralyze spell.
   *
   * @param target The entity that the spell is being cast on.
   */
  override def checkTarget(target: Entity): Unit = { target.checkSpell(this) }

}

