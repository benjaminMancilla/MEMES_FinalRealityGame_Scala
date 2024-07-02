package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState}
import turn.TurnScheduler

class AbstractSelectTargetState(controller: GameController, turnScheduler: TurnScheduler) extends AbstractState {
  override def needInput() = true

  protected val maxTargetIndex: Int = turnScheduler.turn_info.size
  protected var nextState: Option[GameState] = None
  protected var tryTarget: Int = -1

  override def handleInput(input: String): Unit = {
    println("INPUTPLS")
    auxHandleInput(input, maxTargetIndex)

  }

  protected def updateAffectedEntity(): Unit = {
    if (!controller.turnScheduler.turn_info(tryTarget)._1.state) {
      controller.turnScheduler.removeEntity(turnScheduler.turn_info(tryTarget)._1)
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
