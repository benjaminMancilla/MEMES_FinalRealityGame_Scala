package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState}
import entity.Entity
import turn.TurnScheduler

class SelectSpellState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {
  //Hacer el ataque noma y ver si tira expception, retry al estado si pasa eso
  private val maxSpellIndex: Int = turnScheduler.turn_info.size
  private var nextState: Option[GameState] = None
  private val validTarget: Boolean = false
  override def handleInput(input: String): Unit = {
    try {
      val number = input.toInt
      if (number >= 0 && number <= maxSpellIndex) {
        nextState = Some(new SelectTargetState(controller, turnScheduler, entity))

      } else {
        nextState = Some(new SelectTargetState(controller, turnScheduler, entity))
      }
    } catch {
      case e: NumberFormatException =>
        nextState = Some(new SelectTargetState(controller, turnScheduler, entity))
    }
  }

  override def update(): Unit = {
    // Lógica de derrota
  }
}
