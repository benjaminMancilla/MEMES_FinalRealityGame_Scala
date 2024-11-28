package controller.state.actionStates

import controller.GameController
import controller.command.AttackGameCommand
import controller.visitor.GameStateVisitor

/**
 * State representing the selection of a target for an attack.
 *
 * @param controller The game controller managing the game state.
 */
class SelectTargetState(controller: GameController)
  extends AbstractSelectTargetState(controller) {

  /**
   * Executes the attack command on the selected target and updates the game state accordingly.
   */
  override def update(): Unit = {
    // Check if a target has been selected
    if (tryTarget == -1) {
      return
    }

    // Retrieve attacker and selected target entities
    val attacker = controller.turnScheduler.nextAttacker
    val target = controller.turnScheduler.turn_info(tryTarget)._1

    // Create and execute the attack game command
    val attackCommand = new AttackGameCommand(attacker, target, controller)
    val nextStateOption = attackCommand.execute()

    // Update affected entity and set the next state
    updateAffectedEntity(tryTarget)
    nextStateOption.foreach(controller.setState)
  }

  /**
   * Accepts a visitor for visiting this state.
   *
   * @param visitor The visitor to accept.
   */
  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitTarget(this)
  }
}

