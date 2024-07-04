package controller.state.turnStates

import controller.GameController
import controller.state.AbstractState


class ResetBarState(controller: GameController) extends AbstractState(controller) {
  override def update(): Unit = {
    val resetEntity = controller.turnScheduler.nextAttacker
    controller.turnScheduler.resetBarValue(resetEntity)
    controller.setState(new EndTurnState(controller, resetEntity))
  }
}


