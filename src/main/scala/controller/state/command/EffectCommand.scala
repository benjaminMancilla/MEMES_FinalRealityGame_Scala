package controller.state.command

import controller.GameController
import controller.state.actionStates.ActionState
import controller.state.{EndTurnState, GameState}
import entity.Entity

class EffectCommand(target: Entity, controller: GameController) extends Command {
  def execute(): Option[GameState] = {
    if (target.activeEffects.isEmpty){
      return Some(new ActionState(controller))
    }
    for(effect <- target.activeEffects){
      effect.applyEffect(target)
      controller.turnScheduler.updateEntity(target)
      if(!target.state){
        return Some(new EndTurnState(controller))
      }
    }
    if (target.skipTurn) {
      target.removeSkipTurn()
      controller.turnScheduler.updateEntity(target)
      return Some(new EndTurnState(controller))
    }
    Some(new ActionState(controller))
  }


}
