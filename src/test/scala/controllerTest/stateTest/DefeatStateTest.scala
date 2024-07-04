package controllerTest.stateTest

import controller.state.macroStates.DefeatState
import controllerTest.ControllerTest

class DefeatStateTest extends ControllerTest {

  test("DefeatState should set combatResult to Defeat") {
    val victoryState = new DefeatState(controller)
    victoryState.update()

    assert(controller.combatResult == "Defeat", "combatResult should be set to Defeat")
  }

}
