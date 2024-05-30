package party

import entity.character.PICharacter

import scala.collection.mutable.ArrayBuffer

/**
 * Trait representing a player's party in a game.
 * A party consists of a fixed number of characters.
 *
 * @param characters The characters in the party.
 */
trait PIParty {

  /**
   * The characters in the party.
   */
  def characters: ArrayBuffer[PICharacter]

  /**
   * The state of the party.
   * True indicates the party is active, false indicates the party is defeated.
   */
  def partyState: Boolean

}

