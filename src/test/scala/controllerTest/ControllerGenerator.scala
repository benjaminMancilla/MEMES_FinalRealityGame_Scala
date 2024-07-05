package controllerTest

import controller.{GameController, GameControllerConcrete}
import controller.state.GameState
import controller.state.macroStates.StartState
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import controllerTest.MockState
import model.entity.Entity
import model.entity.character.Character
import model.entity.character.commonCharacter.{Ninja, Warrior}
import model.entity.character.magicCharacter.{BlackMage, WhiteMage}
import model.entity.enemy.ConcreteEnemy
import model.party.{ConcreteParty, Party}
import model.turn.TurnScheduler
import model.weapon.Weapon
import model.weapon.commonWeapon.{Axe, Bow}
import model.weapon.magicWeapon.Staff


class ControllerGenerator extends munit.FunSuite {

  var party: Party = _
  var party2: Party = _
  var turnScheduler: TurnScheduler = _
  var turnScheduler2: TurnScheduler = _
  var inventory: List[Weapon] = _
  var actionBarIncrease: Int = _
  var controller: GameControllerConcrete = _
  var controller2: GameControllerConcrete = _


  override def beforeEach(context: BeforeEach): Unit = {
    val gen1 = controllerGenerator(1)
    val gen2 = controllerGenerator(2)
    controller = gen1._1
    actionBarIncrease = gen1._2
    inventory = gen1._3
    turnScheduler = gen1._4
    party = gen1._5

    controller2 = gen2._1
    turnScheduler2 = gen2._4
    party2 = gen2._5

  }

  def controllerGenerator(int: Int): (GameControllerConcrete, Int, List[Weapon], TurnScheduler, Party) = {
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
    if (int == 1){
      (controller, actionBarIncrease, inventory, turnScheduler, party)
    }
    else {
      (controller2, actionBarIncrease, inventory, turnScheduler2, party2)
    }

  }




}

