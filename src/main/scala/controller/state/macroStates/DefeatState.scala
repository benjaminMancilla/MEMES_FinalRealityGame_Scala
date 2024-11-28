package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState

/**
 * Represents the defeat state of the game, where the game ends with a defeat result.
 *
 * @param controller The game controller managing the game state.
 */
class DefeatState(controller: GameController) extends AbstractState(controller) {

  /**
   * Updates the game state to mark it as a defeat.
   * Sets the combat result in the game controller to "Defeat".
   */
  override def update(): Unit = {
    controller.combatResult = "Defeat"
  }
}
