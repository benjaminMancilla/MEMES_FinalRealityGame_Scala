package controllerTest

import controller.GameController
import controller.state.{AbstractState, GameState}

class MockState(controller: GameController) extends AbstractState(controller) {
  var handleInputCalled = false
  var updateCalled = false

  override def needInput(): Boolean = false

  override def handleInput(input: String): Unit = {
    handleInputCalled = true
  }

  override def update(): Unit = {
    updateCalled = true
  }

}
