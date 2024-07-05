package controllerTest.stateTest

import controller.state.macroStates.DefeatState
import controllerTest.ControllerGenerator

class DefeatStateTest extends ControllerGenerator {

  test("DefeatState should set combatResult to Defeat") {
    val victoryState = new DefeatState(controller)
    victoryState.update()

    assert(controller.combatResult == "Defeat", "combatResult should be set to Defeat")
  }

}
