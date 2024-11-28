package controllerTest.stateTest

import controller.state.macroStates.VictoryState
import controllerTest.ControllerGenerator

class VictoryStateTest extends ControllerGenerator {

  test("VictoryState should set combatResult to Victory") {
    val victoryState = new VictoryState(controller)
    victoryState.update()

    assert(controller.combatResult == "Victory", "combatResult should be set to Victory")
  }

}
