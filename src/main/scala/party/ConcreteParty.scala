package party

import entity.character.{Character, PICharacter}

import scala.collection.mutable.ArrayBuffer
//NO PUEDE TENER MAS DE 3
class ConcreteParty (var _characters : ArrayBuffer[Character] =  ArrayBuffer.empty[Character],
                     var _partyState : Boolean = true)
  extends Party {

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
