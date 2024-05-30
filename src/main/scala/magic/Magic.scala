package magic

import entity.Entity
import entity.character.magicCharacter.MagicCharacter


/**
 * Represents magic spells in the game.
 *
 * The `Magic` trait defines the common interface for all magic spells, which can be used by
 * characters that have access to magic. Magic spells can be applied to a target entity
 * and have associated properties like name and mana cost.
 */
trait Magic extends PIMagic {

  /**
   * Applies the effect of the spell to the target.
   *
   * @param sorcerer The magic character casting the spell.
   * @param target The entity that the spell is being cast on.
   * @param mDmg The magic damage or effect value of the spell.
   */
  def applySpell(sorcerer: MagicCharacter, target: Entity, mDmg: Int): Unit

}

