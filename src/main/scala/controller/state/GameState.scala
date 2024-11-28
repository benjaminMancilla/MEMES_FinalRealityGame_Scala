package controller.state

import controller.GameController
import controller.visitor.GameStateVisitor

/**
 * Trait representing various states in the game.
 * Using the state design pattern.
 */
trait GameState {

  /**
   * Handles player input for the current game state.
   *
   * @param input The input provided by the player.
   */
  def handleInput(input: String): Unit

  /**
   * Updates the game state.
   */
  def update(): Unit

  /**
   * Checks if the game state requires player input.
   *
   * @return `true` if input is needed, `false` otherwise.
   */
  def needInput(): Boolean

  /**
   * Retrieves the game controller associated with the game state.
   *
   * @return The game controller managing the game state.
   */
  def getController: GameController

  /**
   * Accepts a visitor for handling game state-specific operations.
   *
   * @param visitor The visitor implementing `GameStateVisitor`.
   */
  def accept(visitor: GameStateVisitor): Unit
}
