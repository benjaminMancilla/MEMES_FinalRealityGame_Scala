import munit.FunSuite
import character.{Character, Warrior}
import party.ConcreteParty

import scala.collection.mutable.ArrayBuffer

class PartyTest extends FunSuite {

  test("ConcreteParty should initialize correctly") {
    val party = new ConcreteParty()
    assert(party.characters.isEmpty)
    assert(party.partyState == true)
  }

  test("Adding and removing characters from ConcreteParty should work correctly") {
    val party = new ConcreteParty()
    val warrior1 = new Warrior("Warrior1", 200, 30, 50, null)
    val warrior2 = new Warrior("Warrior2", 180, 25, 40, null)

    // Adding characters
    party.addCharacter(warrior1)
    assert(party.characters.contains(warrior1))
    assert(party.characters.length == 1)

    // Adding the same character again should not change the party
    party.addCharacter(warrior1)
    assert(party.characters.length == 1)

    // Adding another character
    party.addCharacter(warrior2)
    assert(party.characters.contains(warrior2))
    assert(party.characters.length == 2)

    // Removing a character
    party.removeCharacter(warrior1)
    assert(!party.characters.contains(warrior1))
    assert(party.characters.length == 1)

    // Removing a character that doesn't exist in the party should not change the party
    val warrior3 = new Warrior("Warrior3", 220, 35, 55, null)
    party.removeCharacter(warrior3)
    assert(party.characters.length == 1)
  }

  test("Checking party state should work correctly") {
    val party = new ConcreteParty()
    val warrior1 = new Warrior("Warrior1", 200, 30, 50, null)
    val warrior2 = new Warrior("Warrior2", 180, 25, 40, null)

    // Add characters to the party
    party.addCharacter(warrior1)
    party.addCharacter(warrior2)
    party.checkPartyState()

    // Check party state when all characters are alive
    assert(party.partyState == true)

    // Set one character's state to false
    warrior1.characterState = false
    party.checkPartyState()

    // Check party state when one character is dead
    assert(party.partyState == true)

    warrior2.characterState = false
    party.checkPartyState()

    assert(party.partyState == false)

  }
}

