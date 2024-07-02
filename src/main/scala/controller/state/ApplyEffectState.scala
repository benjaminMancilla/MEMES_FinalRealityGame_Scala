package controller.state

import controller.GameController
import controller.state.actionStates.ActionState
import entity.Entity
import turn.TurnScheduler


class ApplyEffectState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {

  override def update(): Unit = {
    println("EFFECT")
    if (controller.turnScheduler.nextAttacker.activeEffects.isEmpty){
      controller.setState(new ActionState(controller, turnScheduler, entity))
      return
    }
    for(effect <- controller.turnScheduler.nextAttacker.activeEffects){
      effect.applyEffect(controller.turnScheduler.nextAttacker)
      if (!controller.turnScheduler.nextAttacker.state){
        controller.setState(new EndTurnState(controller, turnScheduler))
        return
      }
    }
    if (controller.turnScheduler.nextAttacker.skipTurn) {
      controller.setState(new EndTurnState(controller, turnScheduler))
      return
    }
    controller.setState(new ActionState(controller, turnScheduler, entity))
  }
}
