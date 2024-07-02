package controllerTest.stateTest

import controller.state.{StartState, StartTurnState, UpdateBarState}
import controllerTest.ControllerTest

class StartStateTest extends ControllerTest {

  test("StartState should transition to UpdateBarState if no entities are ready") {
    controller.turnScheduler.turn_ready.clear() // Asegurarse de que no hay entidades listas

    val startState = new StartState(controller)
    controller.setState(startState)
    startState.update()

    assert(controller.currentState.isInstanceOf[UpdateBarState], "Controller should transition to UpdateBarState")
  }

  test("StartState should transition to StartTurnState if there are entities ready") {
    // Asegurarse de que hay al menos una entidad lista
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    val startState = new StartState(controller)
    controller.setState(startState)
    startState.update()

    assert(controller.currentState.isInstanceOf[StartTurnState], "Controller should transition to StartTurnState")
  }
}