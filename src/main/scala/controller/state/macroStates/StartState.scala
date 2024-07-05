package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState
import controller.state.turnStates.StartTurnState

/**
 * Represents the start state of the game, where the game decides whether to update the turn bar or start a new turn.
 *
 * @param controller The game controller managing the game state.
 */
class StartState(controller: GameController) extends AbstractState(controller) {

  /**
   * Updates the game state based on whether there are entities ready to take their turns or not.
   */
  override def update(): Unit = {
    if (controller.turnScheduler.turn_ready.isEmpty) {
      controller.setState(new UpdateBarState(controller))
    } else {
      controller.setState(new StartTurnState(controller))
    }
  }
}

