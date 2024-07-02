package controller.state

import controller.GameController
import controller.visitor.StateVisitor
import entity.Entity
import turn.TurnScheduler

class ResetBarState(controller: GameController) extends AbstractState {
  println("RESET")
  override def update(): Unit = {
    controller.turnScheduler.resetBarValue(controller.turnScheduler.nextAttacker)
    controller.setState(new EndTurnState(controller))
  }
}


