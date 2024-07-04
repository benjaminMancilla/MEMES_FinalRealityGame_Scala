package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState

class VictoryState(controller: GameController) extends AbstractState(controller) {

  override def update(): Unit = {
    controller.combatResult = "Victory"

  }
}
