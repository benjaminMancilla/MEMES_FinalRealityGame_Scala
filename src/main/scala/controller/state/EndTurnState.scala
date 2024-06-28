package controller.state

import controller.GameController
import turn.TurnScheduler

class EndTurnState(controller: GameController, turnScheduler: TurnScheduler) extends GameState {
  override def handleInput(input: String): Unit = {}

  override def update(): Unit = {
//    if (/* Verificar si los enemigos han sido derrotados */) {
//      controller.setState(new VictoryState(controller, turnScheduler))
//    } else if (/* Verificar si el partido ha sido derrotado */) {
//      controller.setState(new DefeatState(controller, turnScheduler))
//    } else {
//      controller.setState(new StartState(controller, turnScheduler))
//    }
  }
}
