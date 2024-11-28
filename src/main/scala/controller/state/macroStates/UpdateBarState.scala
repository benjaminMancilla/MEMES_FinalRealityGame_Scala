package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState
import controller.state.turnStates.StartTurnState

import scala.util.control.Breaks.{break, breakable}

/**
 * Represents a state where the action bars of entities are updated until at least one entity is ready.
 *
 * @param controller The game controller managing the game state.
 */
class UpdateBarState(controller: GameController) extends AbstractState(controller) {

  /**
   * Updates the action bars of entities until at least one entity is ready to perform an action.
   * Once an entity is ready, transitions the game state to StartTurnState.
   */
  override def update(): Unit = {
    // Update the maximum action bars of entities
    controller.turnScheduler.updateMaxBars()

    // Loop until at least one entity is ready
    breakable {
      while (true) {
        if (controller.turnScheduler.turn_ready.nonEmpty) {
          break()
        }
        // Update action progress and check waiting entities
        controller.turnScheduler.updateActionProgress(controller.actionBarIncrease)
        controller.turnScheduler.checkWaitEntities()
      }
    }

    // Transition to StartTurnState once an entity is ready
    controller.setState(new StartTurnState(controller))
  }
}

