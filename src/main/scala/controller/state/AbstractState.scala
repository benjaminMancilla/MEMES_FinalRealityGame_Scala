package controller.state

import controller.GameController
import controller.visitor.GameStateVisitor

/**
 * Abstract base class implementing common behavior for game states.
 *
 * @param controller The game controller managing the game state.
 */
abstract class AbstractState(controller: GameController) extends GameState {

  /**
   * Determines if this state requires player input.
   *
   * @return Always returns `false` by default.
   */
  def needInput(): Boolean = false

  /**
   * Placeholder method for updating the game state.
   * Subclasses should override this method to provide specific state update logic.
   */
  def update(): Unit = {}

  /**
   * Placeholder method for handling player input.
   * Subclasses should override this method to handle specific player inputs.
   *
   * @param input The input provided by the player.
   */
  def handleInput(input: String): Unit = {}

  /**
   * Updates the state of an affected entity based on its index in the turn information.
   *
   * @param index The index of the entity in the turn information list.
   */
  protected def updateAffectedEntity(index: Int): Unit = {
    if (!controller.turnScheduler.turn_info(index)._1.state) {
      controller.turnScheduler.removeEntity(controller.turnScheduler.turn_info(index)._1)
    }
  }

  /**
   * Retrieves the game controller associated with the game state.
   *
   * @return The game controller managing the game state.
   */
  def getController: GameController = controller

  /**
   * Accepts a visitor for handling game state-specific operations.
   * This method is meant to be overridden by subclasses to accept specific visitors.
   *
   * @param visitor The visitor implementing `GameStateVisitor`.
   */
  def accept(visitor: GameStateVisitor): Unit = {}
}

