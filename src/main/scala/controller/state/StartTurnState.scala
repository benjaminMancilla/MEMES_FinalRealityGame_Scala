package controller.state

import controller.GameController
import turn.TurnScheduler

class StartTurnState(controller: GameController) extends AbstractState(controller) {

  override def update(): Unit = {
    println("STARTTURN")
    controller.setState(new ApplyEffectState(controller))
  }
}
