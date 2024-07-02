package controller.state

import controller.GameController
import controller.visitor.StateVisitor
import entity.Entity
import turn.TurnScheduler

class ResetBarState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {
  println("RESET")
  override def update(): Unit = {
    controller.turnScheduler.resetBarValue(entity)
    controller.setState(new EndTurnState(controller, turnScheduler))
  }
}


