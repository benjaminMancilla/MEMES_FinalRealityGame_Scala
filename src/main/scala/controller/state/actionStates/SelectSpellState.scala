package controller.state.actionStates

import controller.GameController
import controller.state.GameState
import entity.Entity
import turn.TurnScheduler

class SelectSpellState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends GameState {
  override def handleInput(input: String): Unit = {
    // Manejar entrada del usuario en caso de derrota
  }

  override def update(): Unit = {
    // Lógica de derrota
  }
}
