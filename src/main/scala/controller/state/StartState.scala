package controller.state

import controller.GameController
import turn.TurnScheduler

class StartState(controller: GameController, turnScheduler: TurnScheduler) extends GameState {
  override def handleInput(input: String): Unit = {}

  override def update(): Unit = {
    if (turnScheduler.turn_ready.isEmpty) {
      controller.setState(new UpdateBarState(controller, turnScheduler))
    } else {
      controller.setState(new StartTurnState(controller, turnScheduler))
    }
  }
}
