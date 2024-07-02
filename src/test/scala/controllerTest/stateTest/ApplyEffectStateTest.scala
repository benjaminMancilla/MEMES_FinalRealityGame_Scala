package controllerTest.stateTest

import controller.state.{ApplyEffectState, EndTurnState, StartTurnState}
import controller.state.actionStates.ActionState
import controller.state.command.EffectCommand
import controllerTest.ControllerTest
import effect.{Paralyzed, Poisoned}

class ApplyEffectStateTest extends ControllerTest{


  test("ApplyEffectState should transition to ActionState when effect is applied successfully") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()
    val state = new ApplyEffectState(controller)
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("ApplyEffectState should transition to EndState if the entity dies") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()

    val effect = new Poisoned(magicDamageI = 1000000)

    target.addEffect(effect)

    val state = new ApplyEffectState(controller)
    state.update()

    assert(controller.currentState.isInstanceOf[EndTurnState], "Controller should transition to EndTurnState")
  }

  test("ApplyEffectState should transition to EndState if the skipTurn condition is on") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()
    val effect = new Paralyzed()

    target.addEffect(effect)

    val state = new ApplyEffectState(controller)
    state.update()

    assert(controller.currentState.isInstanceOf[EndTurnState], "Controller should transition to EndTurnState")
  }



}