package controllerTest

import controller.GameControllerConcrete
import controller.state.{GameState, StartState}
import entity.Entity
import entity.character.commonCharacter.{Ninja, Warrior}
import entity.character.magicCharacter.{BlackMage, WhiteMage}
import entity.character.Character
import entity.enemy.ConcreteEnemy
import munit.FunSuite
import party.{ConcreteParty, Party}
import turn.TurnScheduler
import weapon.Weapon
import weapon.commonWeapon.{Axe, Bow}
import weapon.magicWeapon.Staff

import scala.collection.mutable.ArrayBuffer
import controllerTest.MockState


class ControllerTest extends munit.FunSuite {
  var party: Party = _
  var party2: Party = _
  var turnScheduler: TurnScheduler = _
  var turnScheduler2: TurnScheduler = _
  var inventory: List[Weapon] = _
  var actionBarIncrease: Int = _
  var controller: GameControllerConcrete = _
  var controller2: GameControllerConcrete = _


  override def beforeEach(context: BeforeEach): Unit = {
    val warrior = new Warrior("Heman", 100, 30, 60)
    val blackMage = new BlackMage("Saruman", 60, 10, 20, 100)
    val whiteMage = new WhiteMage("Gandalf", 60, 10, 20, 100)
    val ninja = new Ninja("Garu", 80, 20, 10)
    inventory = List(new Axe("Battle Axe", 60, 20), new Bow("Refined Bow", 60, 20), new Staff("Magic Staff", 70, 15, 50))
    warrior.changeWeapon(Some(inventory.head))
    blackMage.changeWeapon(Some(inventory(2)))
    ninja.changeWeapon(Some(inventory(1)))
    party = new ConcreteParty(ArrayBuffer(warrior, blackMage, ninja))
    party2 = new ConcreteParty(ArrayBuffer(warrior, whiteMage, ninja))
    val turnBuffer: ArrayBuffer[Entity] = ArrayBuffer(
      warrior, blackMage, ninja,
      new ConcreteEnemy("Goblin1", 30, 5, 40, 25),
      new ConcreteEnemy("Goblin2", 30, 5, 20, 15),
      new ConcreteEnemy("Goblin3", 30, 5, 10, 5)
    )
    val turnBuffer2: ArrayBuffer[Entity] = ArrayBuffer(
      warrior, whiteMage, ninja,
      new ConcreteEnemy("Goblin1", 30, 5, 40, 25),
      new ConcreteEnemy("Goblin2", 30, 5, 20, 15),
      new ConcreteEnemy("Goblin3", 30, 5, 10, 5)
    )
    turnScheduler = new TurnScheduler(turnBuffer)
    turnScheduler2 = new TurnScheduler(turnBuffer2)

    actionBarIncrease = 10
    controller = new GameControllerConcrete(party, turnScheduler, actionBarIncrease, inventory)
    controller2 = new GameControllerConcrete(party2, turnScheduler2, actionBarIncrease, inventory)
  }

  test("GameControllerConcrete should be created with correct initial values") {
    assertEquals(controller.actionBarIncrease, actionBarIncrease)
    assertEquals(controller.weaponInventory, inventory)
    assertEquals(controller.turnCurrentSate, turnScheduler.turn_info)
    assert(controller.currentState.isInstanceOf[StartState])
  }

  test("GameControllerConcrete should change state correctly") {
    val newState = new MockState()
    controller.setState(newState)
    assertEquals(controller.currentState, newState)
  }

  test("GameControllerConcrete should delegate handleInput to current state") {
    val mockState = new MockState()
    controller.setState(mockState)
    val input = "someInput"
    controller.handleInput(input)
    assert(mockState.handleInputCalled, "handleInput should have been called on the current state")
  }

  test("GameControllerConcrete should delegate update to current state") {
    val mockState = new MockState()
    controller.setState(mockState)
    controller.update()
    assert(mockState.updateCalled, "update should have been called on the current state")
  }

  test("GameControllerConcrete should return correct actionBarIncrease") {
    assertEquals(controller.actionBarIncrease, actionBarIncrease)
  }

  test("GameControllerConcrete should return correct weaponInventory") {
    assertEquals(controller.weaponInventory, inventory)
  }

  test("GameControllerConcrete should return correct turnCurrentSate") {
    assertEquals(controller.turnCurrentSate, turnScheduler.turn_info)
  }
}

