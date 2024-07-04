package controller.command

import controller.state.GameState

trait Command {
  def execute(): Option[GameState]
}