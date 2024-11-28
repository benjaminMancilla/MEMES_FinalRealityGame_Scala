package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState

/**
 * Represents the victory state of the game, where the game ends with a victory result.
 *
 * @param controller The game controller managing the game state.
 */
class VictoryState(controller: GameController) extends AbstractState(controller) {

  /**
   * Updates the game state to mark it as a victory.
   * Sets the combat result in the game controller to "Victory".
   */
  override def update(): Unit = {
    controller.combatResult = "Victory"
  }
}

