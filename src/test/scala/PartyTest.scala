import entity.character.{BlackMage, Character, Ninja, Warrior}
import exceptions.EntityOverflow
import munit.FunSuite
import party.ConcreteParty

import scala.collection.mutable.ArrayBuffer


class PartyTest extends FunSuite {
  val warrior: Warrior = new Warrior("Heman", 200, 30, 50)
  val ninja: Ninja = new Ninja("Tengen", 120, 15, 10)
  val mage: BlackMage = new BlackMage("elmago", 160, 10, 30, 80)

  test("ConcreteParty should initialize correctly") {
    intercept[EntityOverflow] {
      new ConcreteParty(ArrayBuffer.empty[Character])
    }
    intercept[EntityOverflow] {
      new ConcreteParty(ArrayBuffer[Character](warrior))
    }
    val party = new ConcreteParty(ArrayBuffer(warrior, ninja, mage))
    assert(party.characters(0) == warrior)
    assert(party.characters(1) == ninja)
    assert(party.characters(2) == mage)
  }

  test("Adding and removing characters from ConcreteParty should work correctly") {
    val party = new ConcreteParty(ArrayBuffer(warrior, ninja, mage))
    val warrior1 = new Warrior("Warrior1", 200, 30, 50)
    val warrior2 = new Warrior("Warrior2", 180, 25, 40)

    party.addCharacter(warrior1)
    assert(party.characters.contains(warrior1))
    assert(party.characters.length == 4)

    party.addCharacter(warrior1)
    assert(party.characters.length == 4)

    party.addCharacter(warrior2)
    assert(party.characters.contains(warrior2))
    assert(party.characters.length == 5)

    party.removeCharacter(warrior1)
    assert(!party.characters.contains(warrior1))
    assert(party.characters.length == 4)

    val warrior3 = new Warrior("Warrior3", 220, 35, 55)
    party.removeCharacter(warrior3)
    assert(party.characters.length == 4)
  }

  test("Checking party state should work correctly") {
    val party = new ConcreteParty(ArrayBuffer(warrior, ninja, mage))
    val warrior1 = new Warrior("Warrior1", 200, 30, 50)
    val warrior2 = new Warrior("Warrior2", 180, 25, 40)

    party.addCharacter(warrior1)
    party.addCharacter(warrior2)
    party.checkPartyState()

    assert(party.partyState)

    warrior1.state = false
    party.checkPartyState()

    assert(party.partyState)

    warrior2.state = false
    party.checkPartyState()

    assert(party.partyState)
    warrior.state = false
    ninja.state = false
    mage.state = false
    party.checkPartyState()
    assert(!party.partyState)

  }
}


