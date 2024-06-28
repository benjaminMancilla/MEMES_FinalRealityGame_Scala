package controller.state.actionStates

import controller.GameController
import controller.state.{GameState, ResetBarState}
import controller.visitor.GeneralActionVisitor
import entity.Entity
import turn.TurnScheduler

class ActionState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends GameState {
  private val visitor = new GeneralActionVisitor()
  override def handleInput(input: String): Unit = {
    // Manejar las acciones del jugador o enemigo
  }

  override def update(): Unit = {
    controller.setState(new ResetBarState(controller, turnScheduler, entity))
  }
}
