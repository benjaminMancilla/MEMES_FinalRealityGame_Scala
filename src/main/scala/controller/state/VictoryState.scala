package controller.state

import controller.GameController
import turn.TurnScheduler

class VictoryState(controller: GameController) extends AbstractState {
  override def handleInput(input: String): Unit = {
    // Manejar entrada del usuario en caso de victoria
  }

  override def update(): Unit = {
    // Lógica de victoria
  }
}
