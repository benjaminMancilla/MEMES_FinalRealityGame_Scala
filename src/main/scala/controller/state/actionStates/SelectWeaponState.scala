package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState}
import entity.Entity
import turn.TurnScheduler

class SelectWeaponState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {
  override def handleInput(input: String): Unit = {
    // Manejar entrada del usuario en caso de derrota
  }

  override def update(): Unit = {
    // Lógica de derrota
  }
}
