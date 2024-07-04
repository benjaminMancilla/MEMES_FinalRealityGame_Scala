package controller.state.turnStates

import controller.GameController
import controller.state.AbstractState

class StartTurnState(controller: GameController) extends AbstractState(controller) {

  override def update(): Unit = {
    controller.setState(new ApplyEffectState(controller))
  }
}
