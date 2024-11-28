package controller.state.actionStates

import controller.GameController
import controller.command.SpellGameCommand
import controller.visitor.GameStateVisitor
import model.magic.Magic

/**
 * State representing the phase where a spell target is selected for casting.
 * This state inherits functionality for handling player input to select a target entity.
 *
 * @param controller The game controller managing the game state and turn flow.
 * @param spell The specific spell being cast in this state.
 */
class SelectSpellTargetState(controller: GameController, spell: Magic)
  extends AbstractSelectTargetState(controller: GameController) {

  /**
   * Updates the game state based on the selected target for the spell.
   * Executes the spell command and transitions to the next state based on the command's result.
   */
  override def update(): Unit = {
    if (tryTarget == -1) {
      return
    }

    // Retrieves the attacker and selected target entities based on player input.
    val attacker = controller.turnScheduler.nextAttacker
    val target = controller.turnScheduler.turn_info(tryTarget)._1

    // Creates and executes the spell command to apply the spell effect on the target.
    val spellCommand = new SpellGameCommand(attacker, target, controller, spell)
    val nextStateOption = spellCommand.execute()

    // Updates the affected target entity and transitions to the next state if applicable.
    updateAffectedEntity(tryTarget)
    nextStateOption.foreach(controller.setState)
  }

  /**
   * Accepts a visitor to visit this game state.
   *
   * @param visitor Visitor implementing GameStateVisitor to visit this state.
   */
  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitTarget(this)
  }
}

