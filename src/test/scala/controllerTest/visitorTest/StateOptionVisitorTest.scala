package controllerTest.visitorTest

import controller.GameControllerConcrete
import controller.state.actionStates.{ActionState, SelectSpellState, SelectSpellTargetState, SelectWeaponState}
import controller.state.turnStates.StartTurnState
import controller.visitor.StateOptionVisitor
import controllerTest.ControllerGenerator

import model.entity.character.magicCharacter.{BlackMage, WhiteMage}
import model.entity.enemy.ConcreteEnemy
import model.magic.blackMagic.Fire
import model.party.ConcreteParty
import model.turn.TurnScheduler
import model.weapon.commonWeapon.{Axe, Bow}
import model.weapon.magicWeapon.Staff
import vista.GameView

import scala.collection.mutable.ArrayBuffer

class StateOptionVisitorTest extends ControllerGenerator {

  var view: GameView = _
  var visitor: StateOptionVisitor = _

  override def beforeEach(context: BeforeEach): Unit = {
    super.beforeEach(context)
    view = new GameView(controller)
    visitor = new StateOptionVisitor(view)
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
  }

  test("StateOptionVisitor should call displayOptionSpell for SelectSpellState with valid spells") {
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val state = new SelectSpellState(controller)
    visitor.visitSpell(state)
    val expected = List("Select a spell to cast:\n1. Fire\n2. Thunder")
    assertEquals(view.displayLog, expected)
  }

  test("StateOptionVisitor should call displayOptionWeapon for SelectWeaponState") {
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val view = new GameView(controller)
    val visitor = new StateOptionVisitor(view)
    val state = new SelectWeaponState(controller)
    visitor.visitWeapon(state)
    val expected = List("Select a weapon:\n1. Battle Axe\n2. Refined Bow\n3. Magic Staff")
    assertEquals(view.displayLog, expected)
  }

  test("StateOptionVisitor should call displayOptionTarget for SelectSpellTargetState") {
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val view = new GameView(controller)
    val visitor = new StateOptionVisitor(view)
    val state = new SelectSpellTargetState(controller, new Fire)
    visitor.visitTarget(state)
    val expected = List("Select a target:\n1. Heman\n2. Garu\n3. Goblin1\n4. Goblin2\n5. Goblin3")
    assertEquals(view.displayLog, expected)
  }

  test("StateOptionVisitor should call displayOptionCommon for ActionState with action options") {
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val view = new GameView(controller)
    val visitor = new StateOptionVisitor(view)
    val state = new ActionState(controller)
    visitor.visitAction(state)
    val expected = List(s"${controller.turnScheduler.nextAttacker.name}\nSelect an action:\n1. Attack\n2. Weapon\n3. Pass\n4. Magic")
    assertEquals(view.displayLog, expected)
  }

  test("StateOptionVisitor should do nothing for states that are not select states") {

    val view = new GameView(controller)
    val visitor = new StateOptionVisitor(view)
    val state = new StartTurnState(controller)
    visitor.visitNotSelectState(state)
    assert(view.displayLog.isEmpty)
  }
}

