package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState
import controller.state.turnStates.StartTurnState

class StartState(controller: GameController) extends AbstractState(controller) {

  override def update(): Unit = {
    if (controller.turnScheduler.turn_ready.isEmpty) {
      controller.setState(new UpdateBarState(controller))
    } else {
      controller.setState(new StartTurnState(controller))
    }
  }


}
