package controllerTest.commandTest

import controller.state.actionStates.ActionState
import controller.state.command.ChangeWeaponCommand
import controllerTest.ControllerTest
import entity.character.Character
import entity.enemy.ConcreteEnemy
import munit.FunSuite
import weapon.commonWeapon.{Bow, Sword}
import weapon.magicWeapon.Staff

class ChangeWeaponCommandTest extends ControllerTest {


  test("ChangeWeaponCommand should transition to ActionState when weapon is changed successfully") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val newWeapon = new Staff("Staff", 60, 10, 40)
    val entity = controller.turnScheduler.nextAttacker
    val changeWeaponCommand = new ChangeWeaponCommand(entity, controller, Some(newWeapon))

    val nextStateOption = changeWeaponCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
    val character = entity.asInstanceOf[Character]
    assertEquals(character.equipped_weapon, Some(newWeapon))
  }

  test("ChangeWeaponCommand should transition to ActionState on InvalidCarrier exception") {
    // Assuming InvalidCarrier can occur, although it should not
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val invalidCarrierWeapon = new Sword("InvalidSword", 10, 10)
    // Forcing an InvalidCarrier situation (hypothetically)
    val entity = controller.turnScheduler.nextAttacker
    val changeWeaponCommand = new ChangeWeaponCommand(entity, controller, Some(invalidCarrierWeapon))

    val nextStateOption = changeWeaponCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("ChangeWeaponCommand should return None on InvalidWeaponException") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val invalidWeapon = new Bow("InvalidBow", 10, 10)
    val entity = controller.turnScheduler.nextAttacker
    val changeWeaponCommand = new ChangeWeaponCommand(entity, controller, Some(invalidWeapon))

    val nextStateOption = changeWeaponCommand.execute()
    assert(nextStateOption.isEmpty)
  }
}

