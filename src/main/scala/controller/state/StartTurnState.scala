package controller.state

import controller.GameController
import turn.TurnScheduler

class StartTurnState(controller: GameController, turnScheduler: TurnScheduler) extends GameState {
  override def handleInput(input: String): Unit = {}

  override def update(): Unit = {
    val entity = turnScheduler.dequeueReady()
    controller.setState(new ApplyEffectState(controller, turnScheduler, entity))
  }
}
