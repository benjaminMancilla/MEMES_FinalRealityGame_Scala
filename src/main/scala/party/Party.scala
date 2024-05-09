package party

import entity.character.{Character, PICharacter}

import scala.collection.mutable.ArrayBuffer

//NO PUEDE TENER MAS DE 3, si muere 1 no se saca de la party, solo queda como muerto
trait Party {
  var _characters: ArrayBuffer[Character]
  var _partyState: Boolean

  def addCharacter(character: Character): Unit
  def removeCharacter(character: Character): Unit
  def checkPartyState(): Unit

  def characters: ArrayBuffer[PICharacter]
  def partyState: Boolean

}
