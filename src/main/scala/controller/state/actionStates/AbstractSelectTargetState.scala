package controller.state.actionStates

import controller.state.{AbstractState, GameState}
import turn.TurnScheduler

class AbstractSelectTargetState(turnScheduler: TurnScheduler) extends AbstractState {
  override def needInput() = true

  protected val maxTargetIndex: Int = turnScheduler.turn_info.size
  protected var nextState: Option[GameState] = None
  protected var tryTarget: Int = -1

  override def handleInput(input: String): Unit = {
    auxHandleInput(input, maxTargetIndex)

  }

  protected def updateAffectedEntity(): Unit = {
    if (!turnScheduler.turn_info(tryTarget)._1.state) {
      turnScheduler.removeEntity(turnScheduler.turn_info(tryTarget)._1)
    }
  }

  private def auxHandleInput(input:String, size:Int): Unit = {
    try {
      val number = input.toInt
      if (number >= 0 && number <= size) {
        tryTarget = number
      }
      else { // Invalid Target
      }
    } catch {
      case e: NumberFormatException =>
      //Pon un numero po tontin
    }

  }

}
