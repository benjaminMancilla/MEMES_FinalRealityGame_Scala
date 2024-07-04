package controller.command

import controller.GameController
import controller.state.actionStates.ActionState
import controller.state.turnStates.{EndTurnState, ResetBarState}
import controller.state.GameState
import model.entity.Entity

class EffectCommand(target: Entity, controller: GameController) extends Command {
  def execute(): Option[GameState] = {
    if (target.activeEffects.isEmpty){
      return Some(new ActionState(controller))
    }
    for(effect <- target.activeEffects){
      effect.applyEffect(target)
      controller.turnScheduler.updateEntity(target)
      if(!target.state){
        return Some(new ResetBarState(controller))
      }
    }
    if (target.skipTurn) {
      target.removeSkipTurn()
      controller.turnScheduler.updateEntity(target)
      return Some(new ResetBarState(controller))
    }
    Some(new ActionState(controller))
  }


}
