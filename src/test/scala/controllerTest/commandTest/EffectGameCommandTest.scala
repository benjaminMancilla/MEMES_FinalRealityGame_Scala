package controllerTest.commandTest


import controller.command.EffectGameCommand
import controller.state.actionStates.ActionState
import controller.state.turnStates.{EndTurnState, ResetBarState}
import controllerTest.ControllerGenerator
import model.effect.{Paralyzed, Poisoned}


class EffectGameCommandTest extends ControllerGenerator {

  test("EffectGameCommand should transition to ActionState when target has no active effects") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()

    val command = new EffectGameCommand(target, controller)

    val nextState = command.execute()
    print(nextState)


    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ActionState])
  }

  test("EffectGameCommand should apply effects and transition to EndTurnState if target state becomes false") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()

    val effect = new Poisoned(magicDamageI = 1000000)

    target.addEffect(effect)

    val command = new EffectGameCommand(target, controller)
    val nextState = command.execute()

    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ResetBarState])
  }

  test("EffectGameCommand should transition to EndTurnState if target should skip turn") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.clearEffects()
    val effect = new Paralyzed()

    target.addEffect(effect)

    val command = new EffectGameCommand(target, controller)
    val nextState = command.execute()

    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ResetBarState])
  }

  test("EffectGameCommand should transition to ActionState if target pass all the effects") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val target = controller.turnScheduler.nextAttacker
    target.addEffect(new Poisoned(magicDamageI = 30))

    val command = new EffectGameCommand(target, controller)
    val nextState = command.execute()

    assert(nextState.getOrElse(fail("Expected Some(state), but received None")).isInstanceOf[ActionState])
  }


}
