package controller.command

import controller.GameController
import controller.state.actionStates.ActionState
import controller.state.turnStates.{EndTurnState, ResetBarState}
import controller.state.GameState
import model.entity.Entity

/**
 * Command to execute effects on a target entity.
 *
 * @param target The entity on which effects are to be applied.
 * @param controller The game controller managing the game state.
 */
class EffectGameCommand(target: Entity, controller: GameController) extends GameCommand {

  /**
   * Executes the effect command, applying all active effects on the target entity.
   *
   * @return Optional GameState after executing the effect command.
   */
  def execute(): Option[GameState] = {
    if (target.activeEffects.isEmpty) {
      return Some(new ActionState(controller))
    }
    for (effect <- target.activeEffects) {
      effect.applyEffect(target)
      controller.turnScheduler.updateEntity(target)
      if (!target.state) {
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

