package controller.state

import controller.GameController
import turn.TurnScheduler

class StartTurnState(controller: GameController, turnScheduler: TurnScheduler) extends AbstractState {

  override def update(): Unit = {
    val entity = turnScheduler.dequeueReady()
    controller.setState(new ApplyEffectState(controller, turnScheduler, entity))
  }
}
