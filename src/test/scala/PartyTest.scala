import entity.character.{Character, Warrior}
import munit.FunSuite
import party.ConcreteParty

import scala.collection.mutable.ArrayBuffer

class PartyTest extends FunSuite {



  test("ConcreteParty should initialize correctly") {
    val party = new ConcreteParty()
    assert(party.characters.isEmpty)
    assert(party.partyState)
  }

  test("Adding and removing characters from ConcreteParty should work correctly") {
    val party = new ConcreteParty()
    val warrior1 = new Warrior("Warrior1", 200, 30, 50, null)
    val warrior2 = new Warrior("Warrior2", 180, 25, 40, null)

    party.addCharacter(warrior1)
    assert(party.characters.contains(warrior1))
    assert(party.characters.length == 1)

    party.addCharacter(warrior1)
    assert(party.characters.length == 1)

    party.addCharacter(warrior2)
    assert(party.characters.contains(warrior2))
    assert(party.characters.length == 2)

    party.removeCharacter(warrior1)
    assert(!party.characters.contains(warrior1))
    assert(party.characters.length == 1)

    val warrior3 = new Warrior("Warrior3", 220, 35, 55, null)
    party.removeCharacter(warrior3)
    assert(party.characters.length == 1)
  }

  test("Checking party state should work correctly") {
    val party = new ConcreteParty()
    val warrior1 = new Warrior("Warrior1", 200, 30, 50, null)
    val warrior2 = new Warrior("Warrior2", 180, 25, 40, null)

    party.addCharacter(warrior1)
    party.addCharacter(warrior2)
    party.checkPartyState()

    assert(party.partyState)

    warrior1.state = false
    party.checkPartyState()

    assert(party.partyState)

    warrior2.state = false
    party.checkPartyState()

    assert(!party.partyState)

  }
}

