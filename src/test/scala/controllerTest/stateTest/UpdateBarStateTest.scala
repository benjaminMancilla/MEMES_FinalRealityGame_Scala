package controllerTest.stateTest

import controller.state.macroStates.UpdateBarState
import controller.state.turnStates.StartTurnState
import controllerTest.ControllerTest

class UpdateBarStateTest extends ControllerTest {

  test("UpdateBarState should update the turn scheduler and transition to StartTurnState") {
    // Configuramos el estado inicial como UpdateBarState
    val updateBarState = new UpdateBarState(controller)
    controller.setState(updateBarState)

    // Simulamos la llamada al método update
    updateBarState.update()

    // Verificamos que se haya llamado a updateMaxBars y que el estado haya cambiado a StartTurnState
    assert(controller.currentState.isInstanceOf[StartTurnState], "Controller should transition to StartTurnState")
    assert(controller.turnScheduler.turn_ready.nonEmpty)
  }

  test("UpdateBarState should break loop if turn_ready is nonEmpty") {
    // Configuramos el estado inicial como UpdateBarState
    val updateBarState = new UpdateBarState(controller)
    controller.setState(updateBarState)

    // Simulamos que hay al menos una entidad lista
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    // Simulamos la llamada al método update
    updateBarState.update()

    // Verificamos que se haya salido del bucle y que el estado haya cambiado a StartTurnState
    assert(controller.currentState.isInstanceOf[StartTurnState], "Controller should transition to StartTurnState")
  }
}
