package party

import entity.character.Character


/**
 * Trait representing a player's party in a game.
 * A party consists of a fixed number of characters.
 */
trait Party extends PIParty {

  /**
   * Adds a character to the party.
   *
   * @param character The character to be added.
   */
  def addCharacter(character: Character): Unit

  /**
   * Removes a character from the party.
   *
   * @param character The character to be removed.
   */
  def removeCharacter(character: Character): Unit

  /**
   * Checks the state of the party.
   * If all characters are defeated, the party state is set to false.
   */
  def checkPartyState(): Unit

}

