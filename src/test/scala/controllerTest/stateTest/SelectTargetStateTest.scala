package controllerTest.stateTest

import controller.GameController
import controller.command.ChangeWeaponCommand
import controller.state.actionStates.{SelectTargetState, SelectWeaponState}
import controller.state.turnStates.ResetBarState
import controllerTest.ControllerGenerator
import model.entity.Entity
import model.weapon.Weapon
import munit.FunSuite

import java.lang.ModuleLayer.Controller

class SelectTargetStateTest extends ControllerGenerator {



  test("SelectTargetState should transition to ResetBarState when attack is successful") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    val state = new SelectTargetState(controller)
    state.handleInput("0")
    state.update()

    assert(controller.currentState.isInstanceOf[ResetBarState], "Controller should transition to ResetBarState")
  }

  test("SelectTargetState should transition to SelectWeaponState on EmptyWeaponException") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val command = new ChangeWeaponCommand(controller.turnScheduler.nextAttacker, controller: GameController, None)
    command.execute()
    val state = new SelectTargetState(controller)
    state.handleInput("4")
    state.update()

    assert(controller.currentState.isInstanceOf[SelectWeaponState], "Controller should transition to SelectWeaponState")
  }

  test("SelectTargetState should stay in the same state on ProhibitedTarget exception") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    val state = new SelectTargetState(controller)
    state.handleInput("5")
    state.update()

    assert(controller.currentState.isInstanceOf[SelectTargetState], "Controller should stay in SelectTargetState")
  }

  test("SelectTargetState should not change state if no valid target is selected") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val state = new SelectTargetState(controller)
    val currentState = controller.currentState

    state.handleInput("Hola")
    state.update()
    print(controller.currentState)
    //In this case we have StartState because the controller is new, in practice, the state should be SelectTargetState
    assert(controller.currentState.getClass == currentState.getClass, "Controller should stay in currentState")
  }

  test("SelectTargetState should call updateAffectedEntity after an attack") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    var updateCalled = false
    val state = new SelectTargetState(controller) {
      override def updateAffectedEntity(index: Int): Unit = {
        updateCalled = true
      }
    }

    state.handleInput("4")
    state.update()

    assert(updateCalled, "updateAffectedEntity should be called")
  }
}

