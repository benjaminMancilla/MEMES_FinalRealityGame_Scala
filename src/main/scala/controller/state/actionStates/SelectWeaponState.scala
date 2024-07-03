package controller.state.actionStates

import controller.GameController
import controller.state.command.ChangeWeaponCommand

class SelectWeaponState(controller: GameController) extends AbstractSelectTargetState(controller) {


  override val maxTargetIndex: Int = controller.weaponInventory.size
  override def update(): Unit = {
    println("WEAPON")
    if (tryTarget == -1) {
      nextState.foreach(controller.setState)
      return
    }
    controller.turnScheduler.nextAttacker.changeWeapon(Some(controller.weaponInventory(tryTarget)))
    val entity = controller.turnScheduler.nextAttacker
    val weapon = controller.weaponInventory(tryTarget)

    val weaponCommand = new ChangeWeaponCommand(entity, controller, weapon)
    val nextStateOption = weaponCommand.execute()

    nextStateOption.foreach(controller.setState)
  }
}
