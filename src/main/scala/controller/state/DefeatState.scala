package controller.state

import controller.GameController
import turn.TurnScheduler

class DefeatState(controller: GameController, turnScheduler: TurnScheduler) extends GameState {
  override def handleInput(input: String): Unit = {
    // Manejar entrada del usuario en caso de derrota
  }

  override def update(): Unit = {
    // Lógica de derrota
  }
}
