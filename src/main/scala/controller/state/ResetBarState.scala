package controller.state

import controller.GameController


class ResetBarState(controller: GameController) extends AbstractState(controller) {
  println("RESET")
  override def update(): Unit = {
    controller.turnScheduler.resetBarValue(controller.turnScheduler.nextAttacker)
    controller.setState(new EndTurnState(controller))
  }
}


