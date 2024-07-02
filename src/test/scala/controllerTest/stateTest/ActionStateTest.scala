package controllerTest.stateTest

import controller.state.{EndTurnState, ResetBarState}
import controller.state.actionStates.{ActionState, SelectSpellState, SelectTargetState, SelectWeaponState}
import controllerTest.ControllerTest

class ActionStateTest extends ControllerTest{


  test("ActionState should handle valid input 'Attack'") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val state = new ActionState(controller)
    val input = "Attack"

    state.handleInput(input)
    state.update()

    assert(controller.currentState.isInstanceOf[SelectTargetState])
  }

  test("ActionState should handle valid input 'Weapon'") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val state = new ActionState(controller)
    val input = "Weapon"

    state.handleInput(input)
    state.update()

    assert(controller.currentState.isInstanceOf[SelectWeaponState])
  }

  test("ActionState should handle valid input 'Magic' for a MagicCharacter") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val state = new ActionState(controller)
    val input = "Magic"

    state.handleInput(input)
    state.update()

    assert(controller.currentState.isInstanceOf[SelectSpellState])
  }

  test("ActionState should handle valid input 'Pass'") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val state = new ActionState(controller)
    val input = "Pass"

    state.handleInput(input)
    state.update()

    assert(controller.currentState.isInstanceOf[ResetBarState])
  }


  test("ActionState should default to SelectTargetState if no valid options available") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val state = new ActionState(controller)
    val input = "Invalid"
    state.handleInput(input)
    state.update()

    print(controller.currentState)

    assert(controller.currentState.isInstanceOf[ActionState])
  }

}
