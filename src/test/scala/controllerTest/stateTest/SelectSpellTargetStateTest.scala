package controllerTest.stateTest

import controller.GameController
import controller.command.ChangeWeaponCommand
import controller.state.actionStates.{ActionState, SelectSpellTargetState}
import controller.state.turnStates.ResetBarState
import controllerTest.ControllerGenerator
import model.entity.character.magicCharacter.MagicCharacter
import model.magic.blackMagic.Fire
import model.magic.whiteMagic.Heal
import model.weapon.commonWeapon.Sword


class SelectSpellTargetStateTest extends ControllerGenerator {

  test("SelectSpellTargetState should transition to ResetBarState when spell is cast successfully") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val spell = new Fire() // Assuming constructor parameters for Magic
    val state = new SelectSpellTargetState(controller, spell)
    state.handleInput("3")
    state.update()

    assert(controller.currentState.isInstanceOf[ResetBarState], "Controller should transition to ResetBarState")
  }

  test("SelectSpellTargetState should transition to ActionState on InvalidMagicType") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val invalidSpell = new Heal() // Assuming invalid spell
    val state = new SelectSpellTargetState(controller, invalidSpell)
    state.handleInput("0")
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("SelectSpellTargetState should transition to ActionState on NonMagicalCaster") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()


    val spell = new Fire()
    val state = new SelectSpellTargetState(controller, spell)
    state.handleInput("4")
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("SelectSpellTargetState should transition to ActionState on NoMagicPoints") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    controller.turnScheduler.turn_info(1)._1.asInstanceOf[MagicCharacter].currentMagicPoints = 0
    val spell = new Fire()
    val state = new SelectSpellTargetState(controller, spell)
    state.handleInput("4")
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("SelectSpellTargetState should transition to ActionState on NonMagicWeaponException") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val command = new ChangeWeaponCommand(controller.turnScheduler.nextAttacker, controller, Some(new Sword("Sword", 10, 10)))
    command.execute()

    val spell = new Fire()
    val state = new SelectSpellTargetState(controller, spell)
    state.handleInput("4")
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("SelectSpellTargetState should transition to ActionState on EmptyWeaponException") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val command = new ChangeWeaponCommand(controller.turnScheduler.nextAttacker, controller, None)
    command.execute()

    val spell = new Fire()
    val state = new SelectSpellTargetState(controller, spell)
    state.handleInput("4")
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("SelectSpellTargetState should handle InvalidSpellTarget correctly") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val spell = new Fire()
    val state = new SelectSpellTargetState(controller, spell)
    state.handleInput("0")
    state.update()

    assert(!controller.currentState.isInstanceOf[ResetBarState], "Controller should not transition to ResetBarState")
  }

  test("SelectSpellTargetState should not change state if no valid target is selected") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val state = new SelectSpellTargetState(controller, new Fire())
    val currentState = controller.currentState

    state.handleInput("Hola")
    state.update()

    assert(controller.currentState.getClass == currentState.getClass, "Controller should stay in currentState")
  }

  test("SelectSpellTargetState should call updateAffectedEntity after casting a spell") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    var updateCalled = false
    val state = new SelectSpellTargetState(controller, new Fire()) {
      override def updateAffectedEntity(index: Int): Unit = {
        updateCalled = true
      }
    }

    state.handleInput("4")
    state.update()

    assert(updateCalled, "updateAffectedEntity should be called")
  }
}
