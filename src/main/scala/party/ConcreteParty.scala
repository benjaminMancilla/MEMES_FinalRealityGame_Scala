package party

import entity.character.{Character, PICharacter}
import exceptions.entityE.EntityOverflow

import scala.collection.mutable.ArrayBuffer

/**
 * Concrete class representing a player's party in a game.
 * A party consists of a fixed number of characters.
 *
 * @param charactersI The initial characters in the party.
 */
class ConcreteParty (charactersI : ArrayBuffer[Character])
  extends Party {

  /**
   * The characters in the party.
   */
  private var _characters : ArrayBuffer[Character] = try {
    if (charactersI.nonEmpty) {
      if (charactersI.length == 3) {
        charactersI
      } else {
        throw new EntityOverflow("Party can not have an empty array.")
      }
    } else {
      throw new EntityOverflow("Party must have 3 Characters.")
    }
  } catch {
    case e: EntityOverflow => throw e
  }

  /**
   * The state of the party.
   * True indicates the party is active, false indicates the party is defeated.
   */
  private var _partyState : Boolean = true

  /**
   * Adds a character to the party.
   *
   * @param character The character to be added.
   */
  def addCharacter(character: Character): Unit = {
    if (characters.contains(character)) {}
    else {
      characters_=(_characters.append(character))
    }
  }

  /**
   * Removes a character from the party.
   *
   * @param character The character to be removed.
   */
  def removeCharacter(character: Character): Unit = {
    if (!characters.contains(character)) {}
    else {
      characters_=(_characters.filterInPlace(_ != character))
    }
  }

  /**
   * Checks the state of the party.
   * If all characters are defeated, the party state is set to false.
   */
  def checkPartyState(): Unit = {
    val allCharactersDead = characters.forall(!_.state)
    if (allCharactersDead) {
      partyState = false
    }
    else {
      partyState = true
    }
  }

  /**
   * Gets the characters in the party as an ArrayBuffer of PICharacter.
   *
   * @return The characters in the party.
   */
  def characters: ArrayBuffer[PICharacter] = {
    val piCharactersBuffer = ArrayBuffer.empty[PICharacter]
    _characters.foreach { character =>
      val pi_char: PICharacter = character
      piCharactersBuffer += pi_char
    }
    piCharactersBuffer
  }

  /**
   * Sets the characters of the party.
   */
  private def characters_=(newC: ArrayBuffer[Character]): Unit = {
    _characters = newC
  }
  /**
   * Gets the state of the party.
   *
   * @return The state of the party (true for active, false for defeated).
   */
  def partyState: Boolean = _partyState
  private def partyState_=(newPs: Boolean): Unit = {
    _partyState = newPs
  }

}
