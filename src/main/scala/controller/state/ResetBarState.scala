package controller.state

import controller.GameController
import entity.Entity
import turn.TurnScheduler

class ResetBarState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {
  override def handleInput(input: String): Unit = {}

  override def update(): Unit = {
    // Resetea la barra de la entidad
//    if (/* Verificar si los aliados han ganado */) {
//      controller.setState(new VictoryState(controller, turnScheduler))
//    } else if (/* Verificar si los enemigos han ganado */) {
//      controller.setState(new DefeatState(controller, turnScheduler))
//    } else {
//      controller.setState(new EndTurnState(controller, turnScheduler))
//    }
  }
}
