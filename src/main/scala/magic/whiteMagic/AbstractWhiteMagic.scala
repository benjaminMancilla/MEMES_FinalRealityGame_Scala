package magic.whiteMagic

import entity.character.magicCharacter.WhiteMage
import magic.AbstractMagic

/**
 * Represents an abstract white magicE spell in the game.
 *
 * The `AbstractWhiteMagic` class extends the `AbstractMagic` class and implements the `WhiteMagic` trait.
 * It provides common functionality for all white magicE spells and defines compatibility checks specific to white mages.
 */
abstract class AbstractWhiteMagic extends AbstractMagic with WhiteMagic {

  /**
   * Overrides the compatibility check for a WhiteMage casting the spell.
   *
   * This method is overridden to provide an empty implementation, indicating that the white mage can cast any white magicE spell.
   *
   * @param sorcerer The WhiteMage attempting to cast the spell.
   */
  override def checkSorcerer(sorcerer: WhiteMage): Unit = {}

}

