package controller.state

import controller.GameController
import entity.Entity
import turn.TurnScheduler

class ActionState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends GameState {
  override def handleInput(input: String): Unit = {
    // Manejar las acciones del jugador o enemigo
  }

  override def update(): Unit = {
    controller.setState(new ResetBarState(controller, turnScheduler, entity))
  }
}
