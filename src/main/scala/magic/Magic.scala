package magic

import entity.Entity
import entity.character.magicCharacter.MagicCharacter


/**
 * Represents magicE spells in the game.
 *
 * The `Magic` trait defines the common interface for all magicE spells, which can be used by
 * characters that have access to magicE. Magic spells can be applied to a target entityE
 * and have associated properties like name and mana cost.
 */
trait Magic extends PIMagic {

  /**
   * Applies the effect of the spell to the target.
   *
   * @param sorcerer The magicE character casting the spell.
   * @param target The entityE that the spell is being cast on.
   * @param mDmg The magicE damage or effect value of the spell.
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit

}

