package controllerTest.stateTest

import controller.state.turnStates.{EndTurnState, ResetBarState}
import controllerTest.ControllerGenerator

class ResetBarStateTest extends ControllerGenerator {

  test("ResetBarState should reset the action bar value of the next attacker") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    val attacker = controller.turnScheduler.nextAttacker
    val state = new ResetBarState(controller)
    state.update()


    assert(controller.turnScheduler.turn_info(5)._2 == 0, "The action bar value of the next attacker should be reset to 0")
  }

  test("ResetBarState should transition to EndTurnState") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    val state = new ResetBarState(controller)
    controller.setState(state)
    state.update()

    assert(controller.currentState.isInstanceOf[EndTurnState], "Controller should transition to EndTurnState")
  }


}
