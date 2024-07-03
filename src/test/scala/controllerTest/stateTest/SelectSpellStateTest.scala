package controllerTest.stateTest

import controller.state.actionStates.{ActionState, SelectSpellState, SelectSpellTargetState}
import controllerTest.ControllerTest

class SelectSpellStateTest extends ControllerTest {

  test("SelectSpellState should transition to SelectSpellTargetState when a valid spell is selected") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    print(controller.turnScheduler.nextAttacker)

    val state = new SelectSpellState(controller)
    controller.setState(state)

    state.handleInput("Fire")
    state.update()

    assert(controller.currentState.isInstanceOf[SelectSpellTargetState], "Controller should transition to SelectSpellTargetState")
  }

  test("SelectSpellState should transition to ActionState on InvalidSpellSelector exception") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    val state = new SelectSpellState(controller)
    controller.setState(state)

    state.handleInput("Fire")
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("SelectSpellState should stay in the same state on invalid input") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()


    val state = new SelectSpellState(controller)
    controller.setState(state)
    val currentState = controller.currentState

    state.handleInput("InvalidSpell")
    state.update()

    assert(controller.currentState.getClass == currentState.getClass, "Controller should stay in the same state")
  }

  test("SelectSpellState Fire test") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    print(controller.turnScheduler.nextAttacker)

    val state = new SelectSpellState(controller)
    controller.setState(state)

    state.handleInput("Fire")
    state.update()

    assert(controller.currentState.isInstanceOf[SelectSpellTargetState], "Controller should transition to SelectSpellTargetState")
  }

  test("SelectSpellState Thunder test") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    print(controller.turnScheduler.nextAttacker)

    val state = new SelectSpellState(controller)
    controller.setState(state)

    state.handleInput("Thunder")
    state.update()

    assert(controller.currentState.isInstanceOf[SelectSpellTargetState], "Controller should transition to SelectSpellTargetState")
  }

  test("SelectSpellState Heal test") {
    controller2.turnScheduler.updateActionProgress(1000)
    controller2.turnScheduler.checkWaitEntities()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    print(controller2.turnScheduler.nextAttacker)

    val state = new SelectSpellState(controller2)
    controller2.setState(state)

    state.handleInput("Heal")
    state.update()

    assert(controller2.currentState.isInstanceOf[SelectSpellTargetState], "Controller should transition to SelectSpellTargetState")
  }

  test("SelectSpellState Paralysis test") {
    controller2.turnScheduler.updateActionProgress(1000)
    controller2.turnScheduler.checkWaitEntities()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    print(controller2.turnScheduler.nextAttacker)

    val state = new SelectSpellState(controller2)
    controller2.setState(state)

    state.handleInput("Paralyze")
    state.update()

    assert(controller2.currentState.isInstanceOf[SelectSpellTargetState], "Controller should transition to SelectSpellTargetState")
  }

  test("SelectSpellState Poison test") {
    controller2.turnScheduler.updateActionProgress(1000)
    controller2.turnScheduler.checkWaitEntities()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    print(controller2.turnScheduler.nextAttacker)

    val state = new SelectSpellState(controller2)
    controller2.setState(state)

    state.handleInput("Poison")
    state.update()

    assert(controller2.currentState.isInstanceOf[SelectSpellTargetState], "Controller should transition to SelectSpellTargetState")
  }

}
