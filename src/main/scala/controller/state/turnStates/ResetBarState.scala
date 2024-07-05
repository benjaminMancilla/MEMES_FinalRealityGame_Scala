package controller.state.turnStates

import controller.GameController
import controller.state.AbstractState


/**
 * State representing the reset of action bars for the next entity's turn.
 *
 * @param controller The game controller managing the game state.
 */
class ResetBarState(controller: GameController) extends AbstractState(controller) {
  /**
   * Resets the action bar of the next entity in the turn scheduler and transitions to the EndTurnState.
   * The EndTurnState is instantiated with the entity whose turn has just been reset.
   */
  override def update(): Unit = {
    // Get the next entity whose action bar needs to be reset
    val resetEntity = controller.turnScheduler.nextAttacker

    // Reset the action bar value for the entity
    controller.turnScheduler.resetBarValue(resetEntity)

    // Transition to the EndTurnState with the entity whose turn has been reset
    controller.setState(new EndTurnState(controller, resetEntity))
  }
}



