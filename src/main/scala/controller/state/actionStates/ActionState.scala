package controller.state.actionStates

import controller.GameController
import controller.state.turnStates.ResetBarState
import controller.state.{AbstractState, GameState}
import controller.visitor.{GameStateVisitor, GeneralActionVisitor}

/**
 * State representing the action phase where the next attacker chooses an action.
 *
 * @param controller The game controller managing the game state.
 */
class ActionState(controller: GameController) extends AbstractState(controller) {
  override def needInput(): Boolean = true

  // Visitor to determine valid action options for the next attacker
  private val visitor = new GeneralActionVisitor()

  // Optional next state to transition to after handling input
  private var nextState: Option[GameState] = None

  // Determine valid action options for the next attacker
  controller.turnScheduler.nextAttacker.accept(visitor)
  private val _validOptions: List[String] = visitor.buffer

  /**
   * Handles player input during the action phase.
   *
   * @param input Player input specifying the action to take.
   */
  override def handleInput(input: String): Unit = {
    if (validOptions.contains(input)) {
      input match {
        case "Attack" =>
          nextState = Some(new SelectTargetState(controller))
        case "Weapon" =>
          nextState = Some(new SelectWeaponState(controller))
        case "Magic" =>
          nextState = Some(new SelectSpellState(controller))
        case "Pass" =>
          nextState = Some(new ResetBarState(controller))
        case _ =>
        // Handle additional valid inputs if necessary
      }
    } else if (validOptions.isEmpty) {
      nextState = Some(new SelectTargetState(controller))
    } else {
      nextState = Some(this) // Stay in the current state if input is invalid
    }
  }

  /**
   * Updates the game state based on the next state determined from player input.
   */
  override def update(): Unit = {
    nextState.foreach(controller.setState)
    // If nextState is not set (remains None), the state stays in a loop waiting for valid input.
  }

  /**
   * Accepts a visitor for the game state.
   *
   * @param visitor Visitor implementing GameStateVisitor to visit this state.
   */
  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitAction(this)
  }

  /**
   * Retrieves the valid action options for the next attacker.
   *
   * @return List of valid action options as strings.
   */
  def validOptions: List[String] = _validOptions
}

