package party

import entity.character.Character


trait Party extends PIParty {
  def addCharacter(character: Character): Unit
  def removeCharacter(character: Character): Unit
  def checkPartyState(): Unit


}
