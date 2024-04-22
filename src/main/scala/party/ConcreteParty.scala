package party

import entity.character.Character
import scala.collection.mutable.ArrayBuffer

class ConcreteParty (var _characters : ArrayBuffer[Character] =  ArrayBuffer.empty[Character],
                     var _partyState : Boolean = true)
  extends Party {

  def addCharacter(character: Character): Unit = {
    if (characters.contains(character)) {}
    else {
      characters_=(characters.append(character))
    }
  }
  def removeCharacter(character: Character): Unit = {
    if (!characters.contains(character)) {}
    else {
      characters_=(characters.filterInPlace(_ != character))
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

  def characters: ArrayBuffer[Character] = _characters
  def characters_=(newc: ArrayBuffer[Character]): Unit = {
    _characters = newc
  }
  def partyState: Boolean = _partyState
  def partyState_=(newps: Boolean): Unit = {
    _partyState = newps
  }

}
