package controller.state

import controller.GameController
import controller.state.actionStates.ActionState
import controller.state.command.EffectCommand
import entity.Entity
import turn.TurnScheduler


class ApplyEffectState(controller: GameController) extends AbstractState {
  override def needInput() = false

  override def update(): Unit = {
    println("EFFECT")
    val target = controller.turnScheduler.nextAttacker

    val effectCommand = new EffectCommand(target, controller)
    val nextStateOption = effectCommand.execute()

    nextStateOption.foreach(controller.setState)
  }
}
