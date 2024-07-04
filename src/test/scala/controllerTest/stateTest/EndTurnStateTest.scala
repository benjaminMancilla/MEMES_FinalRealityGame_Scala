package controllerTest.stateTest

import controller.state.macroStates.{DefeatState, UpdateBarState, VictoryState}
import controller.state.turnStates.EndTurnState
import controllerTest.ControllerTest

class EndTurnStateTest extends ControllerTest {

  test("EndTurnState should transition to VictoryState when no enemies remain") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.removeEntity( controller.turnScheduler.turn_info(3)._1)
    controller.turnScheduler.removeEntity( controller.turnScheduler.turn_info(3)._1)
    controller.turnScheduler.removeEntity( controller.turnScheduler.turn_info(3)._1)

    val state = new EndTurnState(controller, controller.turnScheduler.nextAttacker)
    state.update()

    assert(controller.currentState.isInstanceOf[VictoryState], "Controller should transition to VictoryState")
  }

  test("EndTurnState should transition to DefeatState when no characters remain") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.removeEntity( controller.turnScheduler.turn_info(0)._1)
    controller.turnScheduler.removeEntity( controller.turnScheduler.turn_info(0)._1)
    controller.turnScheduler.removeEntity( controller.turnScheduler.turn_info(0)._1)

    val state = new EndTurnState(controller, controller.turnScheduler.nextAttacker)
    state.update()

    assert(controller.currentState.isInstanceOf[DefeatState], "Controller should transition to DefeatState")
  }

  test("EndTurnState should transition to UpdateBarState when both characters and enemies remain") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()

    val state = new EndTurnState(controller, controller.turnScheduler.nextAttacker)
    state.update()

    // Assertion: Check if the controller transitions to UpdateBarState
    assert(controller.currentState.isInstanceOf[UpdateBarState], "Controller should transition to UpdateBarState")
  }

  test("EndTurnState should remove entity if its state is false") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val entity = controller.turnScheduler._turn_info(5)._1
    controller.turnScheduler._turn_info(5)._1.state = false
    val state = new EndTurnState(controller, controller.turnScheduler.nextAttacker)
    state.update()

    assert(!controller.turnScheduler.turn_info.exists(_._1 == entity), "Entity with false state should be removed")
  }
}
