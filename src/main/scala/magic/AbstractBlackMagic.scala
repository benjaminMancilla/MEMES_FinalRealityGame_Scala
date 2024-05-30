package magic

import entity.character.BlackMage

/**
 * Represents an abstract black magic spell in the game.
 *
 * The `AbstractBlackMagic` class extends the `AbstractMagic` class and implements the `BlackMagic` trait.
 * It provides common functionality for all black magic spells and defines compatibility checks specific to black mages.
 */
abstract class AbstractBlackMagic extends AbstractMagic with BlackMagic {

  /**
   * Overrides the compatibility check for a BlackMage casting the spell.
   *
   * This method is overridden to provide an empty implementation, indicating that the black mage can cast any black magic spell.
   *
   * @param sorcerer The BlackMage attempting to cast the spell.
   */
  override def checkSorcerer(sorcerer: BlackMage): Unit = {}

}

