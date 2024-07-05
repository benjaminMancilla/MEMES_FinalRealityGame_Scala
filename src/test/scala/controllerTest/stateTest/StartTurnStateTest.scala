package controllerTest.stateTest

import controller.state.turnStates.{ApplyEffectState, StartTurnState}
import controllerTest.ControllerGenerator

class StartTurnStateTest extends ControllerGenerator {

  test("StartTurnState should transition to ApplyEffectState when update is called") {
    val state = new StartTurnState(controller)
    controller.setState(state)
    state.update()

    assert(controller.currentState.isInstanceOf[ApplyEffectState], "Controller should transition to ApplyEffectState")
  }

}
