package controllerTest.stateTest

import controller.state.turnStates.{ApplyEffectState, StartTurnState}
import controllerTest.ControllerTest

class StartTurnStateTest extends ControllerTest {

  test("StartTurnState should transition to ApplyEffectState when update is called") {
    val state = new StartTurnState(controller)
    controller.setState(state)
    state.update()

    assert(controller.currentState.isInstanceOf[ApplyEffectState], "Controller should transition to ApplyEffectState")
  }

  test("StartTurnState should print STARTTURN when update is called") {
    val state = new StartTurnState(controller)
    controller.setState(state)

    // Capture the output
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      state.update()
    }

    val output = stream.toString.trim
    assert(output == "STARTTURN", s"Expected output to be 'STARTTURN', but got '$output'")
  }
}
