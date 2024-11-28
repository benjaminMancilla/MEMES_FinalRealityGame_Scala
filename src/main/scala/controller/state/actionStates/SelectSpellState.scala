package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState}
import controller.visitor.{GameStateVisitor, GeneralActionVisitor, SelectSpellVisitor}
import exceptions.stateE.InvalidSpellSelector
import model.magic.blackMagic.{Fire, Thunder}
import model.magic.whiteMagic.{Heal, Paralyze, Poison}


/**
 * State representing the phase where a magic character selects a spell to cast.
 * This state handles player input to select from available spells for the current attacker.
 *
 * @param controller The game controller managing the game state and turn flow.
 */
class SelectSpellState(controller: GameController) extends AbstractState(controller) {
  // Indicates that input is required from the player during this state phase.
  override def needInput(): Boolean = true

  // Visitor used to determine valid spell options for the next attacker.
  private val visitor = new SelectSpellVisitor()

  // Optional next state to transition to after handling player input.
  private var nextState: Option[GameState] = None

  /**
   * Handles player input during the spell selection phase.
   * Depending on the input, transitions to the corresponding spell targeting state.
   *
   * @param input Player input specifying the spell to cast.
   */
  override def handleInput(input: String): Unit = {
    try {
      // Accepts the visitor to retrieve valid spell options for the next attacker.
      controller.turnScheduler.nextAttacker.accept(visitor)
    } catch {
      case e: InvalidSpellSelector =>
        // If an invalid spell selector exception occurs, return to the action state.
        nextState = Some(new ActionState(controller))
    }

    // Retrieves valid spell options from the visitor's buffer.
    val validOptions: List[String] = visitor.buffer

    // Handles the player input based on the valid spell options.
    if (validOptions.contains(input)) {
      input match {
        case "Fire" =>
          nextState = Some(new SelectSpellTargetState(controller, new Fire))
        case "Thunder" =>
          nextState = Some(new SelectSpellTargetState(controller, new Thunder))
        case "Poison" =>
          nextState = Some(new SelectSpellTargetState(controller, new Poison))
        case "Paralyze" =>
          nextState = Some(new SelectSpellTargetState(controller, new Paralyze))
        case "Heal" =>
          nextState = Some(new SelectSpellTargetState(controller, new Heal))
        case _ =>
        // Additional valid inputs can be handled here if required.
      }
    }
    // If input is not valid and no nextState is set, the state remains in the current spell selection phase.
  }

  /**
   * Updates the game state based on the nextState determined from player input.
   */
  override def update(): Unit = {
    nextState.foreach(controller.setState)
    // If nextState remains None (not set), the state continues to wait for valid player input.
  }

  /**
   * Accepts a visitor to visit this game state.
   *
   * @param visitor Visitor implementing GameStateVisitor to visit this state.
   */
  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitSpell(this)
  }
}

