package controller.state

import controller.GameController
import turn.TurnScheduler

class StartTurnState(controller: GameController, turnScheduler: TurnScheduler) extends AbstractState {

  override def update(): Unit = {
    println("STARTTURN")
    val entity = controller.turnScheduler.nextAttacker
    controller.setState(new ApplyEffectState(controller, turnScheduler, entity))
  }
}
