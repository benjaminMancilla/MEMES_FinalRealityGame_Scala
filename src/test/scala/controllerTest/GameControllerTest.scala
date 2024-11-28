package controllerTest

import controller.state.macroStates.StartState


class GameControllerTest extends ControllerGenerator {


  test("GameControllerConcrete should be created with correct initial values") {
    assertEquals(controller.actionBarIncrease, actionBarIncrease)
    assertEquals(controller.weaponInventory, inventory)
    assertEquals(controller.turnCurrentSate, turnScheduler.turn_info)
    assert(controller.currentState.isInstanceOf[StartState])
  }

  test("GameControllerConcrete should change state correctly") {
    val newState = new MockState(controller)
    controller.setState(newState)
    assertEquals(controller.currentState, newState)
  }

  test("GameControllerConcrete should delegate handleInput to current state") {
    val mockState = new MockState(controller)
    controller.setState(mockState)
    val input = "someInput"
    controller.handleInput(input)
    assert(mockState.handleInputCalled, "handleInput should have been called on the current state")
  }

  test("GameControllerConcrete should delegate update to current state") {
    val mockState = new MockState(controller)
    controller.setState(mockState)
    controller.update()
    assert(mockState.updateCalled, "update should have been called on the current state")
  }

  test("GameControllerConcrete should return correct actionBarIncrease") {
    assertEquals(controller.actionBarIncrease, actionBarIncrease)
  }

  test("GameControllerConcrete should return correct weaponInventory") {
    assertEquals(controller.weaponInventory, inventory)
  }

  test("GameControllerConcrete should return correct turnCurrentSate") {
    assertEquals(controller.turnCurrentSate, turnScheduler.turn_info)
  }

}
