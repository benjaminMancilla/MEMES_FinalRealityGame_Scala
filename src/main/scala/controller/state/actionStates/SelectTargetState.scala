package controller.state.actionStates

import controller.GameController
import controller.state.command.AttackCommand

class SelectTargetState(controller: GameController)
  extends AbstractSelectTargetState(controller: GameController) {

  override def update(): Unit = {
    println("SELECTTARGET")
    if (tryTarget == -1) {
      return
    }

    val attacker = controller.turnScheduler.nextAttacker
    val target = controller.turnScheduler.turn_info(tryTarget)._1

    val attackCommand = new AttackCommand(attacker, target, controller)
    val nextStateOption = attackCommand.execute()
    updateAffectedEntity()

    nextStateOption.foreach(controller.setState)
  }
}
