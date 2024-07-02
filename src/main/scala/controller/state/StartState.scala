package controller.state

import controller.GameController
import turn.TurnScheduler

class StartState(controller: GameController, turnScheduler: TurnScheduler) extends AbstractState {

  override def update(): Unit = {
    if (turnScheduler.turn_ready.isEmpty) {
      controller.setState(new UpdateBarState(controller, turnScheduler))
    } else {
      controller.setState(new StartTurnState(controller, turnScheduler))
    }
  }


}
