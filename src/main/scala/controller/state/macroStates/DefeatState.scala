package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState

class DefeatState(controller: GameController) extends AbstractState(controller) {

  override def update(): Unit = {
    controller.combatResult = "Defeat"
  }
}
