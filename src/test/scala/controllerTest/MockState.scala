package controllerTest

import controller.state.GameState

class MockState extends GameState {
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
