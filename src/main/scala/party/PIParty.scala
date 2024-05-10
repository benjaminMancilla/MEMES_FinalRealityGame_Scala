package party

import entity.character.PICharacter

import scala.collection.mutable.ArrayBuffer

trait PIParty {
  def characters: ArrayBuffer[PICharacter]
  def partyState: Boolean

}
