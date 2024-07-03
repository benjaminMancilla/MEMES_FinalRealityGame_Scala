package controllerTest.commandTest

import controller.state.ResetBarState
import controller.state.actionStates.{SelectTargetState, SelectWeaponState}
import controller.state.command.AttackCommand
import controllerTest.ControllerTest
import weapon.commonWeapon.Sword

class AttackCommandTest extends ControllerTest  {


  test("AttackCommand should transition to ResetBarState when attack is successful") {
    val target = controller.turnScheduler.turn_info(3)._1
    val attacker = controller.turnScheduler.turn_info(0)._1
    attacker.changeWeapon(Some(new Sword("Sword", 10, 10)))
    val attackCommand = new AttackCommand(attacker, target, controller)

    val nextStateOption = attackCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ResetBarState])
  }

  test("AttackCommand should transition to SelectWeaponState when EmptyWeaponException is thrown") {
    val target = controller.turnScheduler.turn_info(3)._1
    val attacker = controller.turnScheduler.turn_info(0)._1
    attacker.changeWeapon(None)
    val attackCommand = new AttackCommand(attacker, target, controller)

    val nextStateOption = attackCommand.execute()
    print(nextStateOption)

    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[SelectWeaponState])
  }

  test("AttackCommand should return the currentState when ProhibitedTarget exception is thrown") {
    val ally = controller.turnScheduler.turn_info(1)._1
    val attacker = controller.turnScheduler.turn_info(0)._1
    val attackCommand = new AttackCommand(attacker, ally, controller)

    val nextStateOption = attackCommand.execute()
    print(nextStateOption)
    assert(nextStateOption.get.isInstanceOf[SelectTargetState])
  }

  test("AttackCommand should update entities in the turn scheduler") {
    val attacker = controller.turnScheduler.turn_info(0)._1
    val target = controller.turnScheduler.turn_info(3)._1
    attacker.changeWeapon(Some(new Sword("Sword", 100, 10)))
    val attackCommand = new AttackCommand(attacker, target, controller)

    val nextStateOption = attackCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ResetBarState])

    val updatedAttacker = controller.turnScheduler.turn_info(0)._1
    val updatedTarget = controller.turnScheduler.turn_info(3)._1
    assert(updatedAttacker == attacker)
    assert(updatedTarget == target)
  }
}

