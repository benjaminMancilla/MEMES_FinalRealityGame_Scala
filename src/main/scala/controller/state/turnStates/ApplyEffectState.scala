package controller.state.turnStates

import controller.GameController
import controller.command.EffectGameCommand
import controller.state.AbstractState


/**
 * State representing the application of effects to the next attacker in the game.
 *
 * @param controller The game controller managing the game state.
 */
class ApplyEffectState(controller: GameController) extends AbstractState(controller) {

  /**
   * Determines if this state requires player input.
   *
   * @return Always returns `false`, indicating no player input is needed.
   */
  override def needInput(): Boolean = false

  /**
   * Updates the game state by applying effects to the next attacker in the turn scheduler.
   * Executes an effect game command and transitions to the resulting state.
   */
  override def update(): Unit = {
    val target = controller.turnScheduler.nextAttacker

    // Create and execute the effect game command
    val effectCommand = new EffectGameCommand(target, controller)
    val nextStateOption = effectCommand.execute()

    // Transition to the next state based on the command execution result
    nextStateOption.foreach(controller.setState)
  }
}

