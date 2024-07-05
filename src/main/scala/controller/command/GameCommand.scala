package controller.command

import controller.state.GameState

/**
 * Trait representing a command in the Command design pattern.
 * Each command encapsulates an action that can be executed.
 * The execute method should be implemented by concrete command classes.
 * It returns an optional GameState representing the state after executing the command.
 */
trait GameCommand {
  /**
   * Executes the command and returns an optional GameState.
   *
   * @return Optional GameState after executing the command.
   */
  def execute(): Option[GameState]
}