package controller.state.turnStates

import controller.GameController
import controller.command.EffectCommand
import controller.state.AbstractState


class ApplyEffectState(controller: GameController) extends AbstractState(controller) {
  override def needInput() = false

  override def update(): Unit = {
    val target = controller.turnScheduler.nextAttacker

    val effectCommand = new EffectCommand(target, controller)
    val nextStateOption = effectCommand.execute()

    nextStateOption.foreach(controller.setState)
  }
}
