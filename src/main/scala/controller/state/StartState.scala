package controller.state

import controller.GameController
import turn.TurnScheduler

class StartState(controller: GameController, turnScheduler: TurnScheduler) extends AbstractState {

  override def update(): Unit = {
    println("START")
    if (controller.turnScheduler.turn_ready.isEmpty) {
      controller.setState(new UpdateBarState(controller, turnScheduler))
    } else {
      controller.setState(new StartTurnState(controller, turnScheduler))
    }
  }


}
