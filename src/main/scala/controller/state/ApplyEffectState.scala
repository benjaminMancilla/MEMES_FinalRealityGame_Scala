package controller.state

import controller.GameController
import controller.state.actionStates.ActionState
import entity.Entity
import turn.TurnScheduler


class ApplyEffectState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {

  override def update(): Unit = {
    if (entity.activeEffects.isEmpty){
      controller.setState(new ActionState(controller, turnScheduler, entity))
      return
    }
    for(effect <- entity.activeEffects){
      effect.applyEffect(entity)
      if (!entity.state){
        controller.setState(new EndTurnState(controller, turnScheduler))
        return
      }
    }
    if (entity.skipTurn) {
      controller.setState(new EndTurnState(controller, turnScheduler))
      return
    }
    controller.setState(new ActionState(controller, turnScheduler, entity))
  }
}
