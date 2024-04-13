package party

import scala.collection.mutable.ArrayBuffer
import character.Character

trait Party {
  var _characters: ArrayBuffer[Character]
  var _partyState: Boolean

  def addCharacter(character: Character): Unit
  def removeCharacter(character: Character): Unit
  def checkPartyState(): Unit

  def characters: ArrayBuffer[Character]
  def characters_=(newc: ArrayBuffer[Character]): Unit
  def partyState: Boolean
  def partyState_=(newps: Boolean): Unit

}
