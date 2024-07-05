package controller.state.turnStates

import controller.GameController
import controller.state.AbstractState

/**
 * State representing the start of a new turn for entities in the game.
 *
 * @param controller The game controller managing the game state.
 */
class StartTurnState(controller: GameController) extends AbstractState(controller) {
  /**
   * Initiates the application of effects for the next attacking entity and transitions to the ApplyEffectState.
   */
  override def update(): Unit = {
    // Transition to the ApplyEffectState to apply effects for the next attacking entity
    controller.setState(new ApplyEffectState(controller))
  }
}

