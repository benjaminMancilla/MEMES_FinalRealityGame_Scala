package controllerTest.commandTest


import controller.command.EffectCommand
import controller.state.actionStates.ActionState
import controller.state.turnStates.{EndTurnState, ResetBarState}
import controllerTest.ControllerTest
import model.effect.{Paralyzed, Poisoned}


class EffectCommandTest extends ControllerTest {

  test("EffectCommand should transition to ActionState when target has no active effects") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()

    val command = new EffectCommand(target, controller)

    val nextState = command.execute()
    print(nextState)


    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ActionState])
  }

  test("EffectCommand should apply effects and transition to EndTurnState if target state becomes false") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()

    val effect = new Poisoned(magicDamageI = 1000000)

    target.addEffect(effect)

    val command = new EffectCommand(target, controller)
    val nextState = command.execute()

    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ResetBarState])
  }

  test("EffectCommand should transition to EndTurnState if target should skip turn") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()
    val effect = new Paralyzed()

    target.addEffect(effect)

    val command = new EffectCommand(target, controller)
    val nextState = command.execute()

    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ResetBarState])
  }

  test("EffectCommand should transition to ActionState if target pass all the effects") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.addEffect(new Poisoned(magicDamageI = 30))

    val command = new EffectCommand(target, controller)
    val nextState = command.execute()

    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ActionState])
  }


}
