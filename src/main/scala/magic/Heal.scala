package magic

import entity.Entity
import entity.character.MagicCharacter

/**
 * Represents the Heal spell in the game.
 *
 * The `Heal` class extends the `AbstractWhiteMagic` class and implements the `DefensiveSpell` trait.
 * It defines the characteristics and behavior of the Heal spell, including its name, mana cost,
 * and the application of its effect to the target.
 */
class Heal extends AbstractWhiteMagic with DefensiveSpell {
  /** The name of the Heal spell. */
  val _name: String = "Heal"
  /** The mana cost of the Heal spell. */
  val _manaCost: Int = 15

  /**
   * Applies the Heal spell's effect to the target.
   *
   * The effect of the Heal spell is to restore a portion of the target's hit points based on its maximum hit points.
   * Specifically, the heal amount is calculated as 30% of the target's maximum hit points.
   *
   * @param sorcerer The magic character casting the spell.
   * @param target The entity that the spell is being cast on.
   * @param mDmg The magic damage (unused parameter, as Heal is a restorative spell).
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit = {
    val heal = (target.hit_points / 10) * 3
    sorcerer.doHeal(target, heal)
  }

  /**
   * Checks if the target is valid for the Heal spell.
   *
   * This method ensures that the target can be affected by the Heal spell.
   *
   * @param target The entity that the spell is being cast on.
   */
  override def checkTarget(target: Entity): Unit = { target.checkSpell(this) }

}

