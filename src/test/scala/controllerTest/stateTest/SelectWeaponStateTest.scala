package controllerTest.stateTest

import controller.state.actionStates.{ActionState, SelectWeaponState}
import controller.state.macroStates.StartState
import controllerTest.ControllerTest
import model.weapon.commonWeapon.{Axe, Sword}

class SelectWeaponStateTest extends ControllerTest {

  test("SelectWeaponState should transition to ActionState when weapon is changed successfully") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val weapon = new Sword("Excalibur", 10, 10) // Assuming a weapon class
    controller.addWeapon(weapon)// Add the weapon to the inventory
    val state = new SelectWeaponState(controller)
    state.handleInput("3") // Excalibur in 3
    state.update()

    assert(controller.currentState.isInstanceOf[ActionState], "Controller should transition to ActionState")
  }

  test("SelectWeaponState should not change state on InvalidWeaponException") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val invalidWeapon = new Axe("Invalid Axe", 10, 10)
    controller.addWeapon(invalidWeapon)
    val state = new SelectWeaponState(controller)
    state.handleInput("3")
    state.update()

    assert(controller.currentState.isInstanceOf[StartState], "Controller should stay in SelectWeaponState")
  }

  test("SelectWeaponState should not change state if no valid weapon is selected") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val state = new SelectWeaponState(controller)
    val currentState = controller.currentState

    state.handleInput("Hola")
    state.update()

    assert(controller.currentState.getClass == currentState.getClass, "Controller should stay in currentState")
  }

  test("SelectWeaponState should call updateAffectedEntity after changing a weapon") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    var updateCalled = false
    val weapon = new Sword("Excalibur", 10, 10)
    controller.addWeapon(weapon)

    val state = new SelectWeaponState(controller) {
      override def updateAffectedEntity(index: Int): Unit = {
        updateCalled = true
      }
    }

    state.handleInput("3")
    state.update()

    assert(updateCalled, "updateAffectedEntity should be called")
  }

}
