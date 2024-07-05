package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState}

/**
 * Abstract state for selecting a target entity in the game.
 *
 * @param controller The game controller managing the game state.
 */
abstract class AbstractSelectTargetState(controller: GameController) extends AbstractState(controller) {
  override def needInput() = true

  // Maximum index for selecting a target
  protected val maxTargetIndex: Int = controller.turnScheduler.turn_info.size

  // Optional next state to transition to after selecting a target
  protected var nextState: Option[GameState] = None

  // Index of the selected target
  protected var tryTarget: Int = -1

  /**
   * Handles player input for selecting a target entity.
   *
   * @param input Player input specifying the target.
   */
  override def handleInput(input: String): Unit = {
    auxHandleInput(input, maxTargetIndex)
  }

  /**
   * Auxiliary method to handle input validation for selecting a target entity.
   *
   * @param input Player input specifying the target.
   * @param size Maximum number of targets available.
   */
  private def auxHandleInput(input: String, size: Int): Unit = {
    try {
      val number = input.toInt
      if (number >= 0 && number <= size - 1) {
        tryTarget = number
      } else {
        // Handle invalid target index
      }
    } catch {
      case e: NumberFormatException =>
      // Handle non-numeric input
    }
  }
}

