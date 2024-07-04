package controller.state.actionStates

import controller.GameController
import controller.command.AttackCommand
import controller.visitor.GameStateVisitor

class SelectTargetState(controller: GameController)
  extends AbstractSelectTargetState(controller: GameController) {

  override def update(): Unit = {
    if (tryTarget == -1) {
      return
    }

    val attacker = controller.turnScheduler.nextAttacker
    val target = controller.turnScheduler.turn_info(tryTarget)._1

    val attackCommand = new AttackCommand(attacker, target, controller)
    val nextStateOption = attackCommand.execute()
    updateAffectedEntity(tryTarget)

    nextStateOption.foreach(controller.setState)
  }

  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitTarget(this)
  }
}
