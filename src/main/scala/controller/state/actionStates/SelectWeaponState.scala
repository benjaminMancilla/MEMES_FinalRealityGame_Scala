package controller.state.actionStates

import controller.GameController
import controller.command.ChangeWeaponCommand
import controller.visitor.GameStateVisitor

class SelectWeaponState(controller: GameController) extends AbstractSelectTargetState(controller) {


  override val maxTargetIndex: Int = controller.weaponInventory.size
  override def update(): Unit = {
    if (tryTarget == -1) {
      nextState.foreach(controller.setState)
      return
    }
    val entity = controller.turnScheduler.nextAttacker
    val weapon = controller.weaponInventory(tryTarget)

    val weaponCommand = new ChangeWeaponCommand(entity, controller, Some(weapon))
    val nextStateOption = weaponCommand.execute()
    updateAffectedEntity(tryTarget)

    nextStateOption.foreach(controller.setState)
  }

  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitWeapon(this)
  }
}
