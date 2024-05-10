package party

import entity.character.{Character, PICharacter}

import scala.collection.mutable.ArrayBuffer
class ConcreteParty (charactersI : ArrayBuffer[Character])
  extends Party {

  private var _characters : ArrayBuffer[Character] = try {
    if (charactersI.nonEmpty) {
      if (charactersI.length == 3) {
        charactersI
      } else {
        throw new IllegalArgumentException("Party can not have an empty array.")
      }
    } else {
      throw new IllegalArgumentException("Party must have 3 Characters.")
    }
  } catch {
    case e: IllegalArgumentException => throw e
  }
  private var _partyState : Boolean = true

  def addCharacter(character: Character): Unit = {
    if (characters.contains(character)) {}
    else {
      characters_=(_characters.append(character))
    }
  }
  def removeCharacter(character: Character): Unit = {
    if (!characters.contains(character)) {}
    else {
      characters_=(_characters.filterInPlace(_ != character))
    }
  }

  def checkPartyState(): Unit = {
    val allCharactersDead = characters.forall(!_.state)
    if (allCharactersDead) {
      partyState = false
    }
    else {
      partyState = true
    }
  }

  def characters: ArrayBuffer[PICharacter] = {
    val piCharactersBuffer = ArrayBuffer.empty[PICharacter]
    _characters.foreach { character =>
      val pi_char: PICharacter = character
      piCharactersBuffer += pi_char
    }
    piCharactersBuffer
  }
  private def characters_=(newC: ArrayBuffer[Character]): Unit = {
    _characters = newC
  }
  def partyState: Boolean = _partyState
  private def partyState_=(newPs: Boolean): Unit = {
    _partyState = newPs
  }

}
