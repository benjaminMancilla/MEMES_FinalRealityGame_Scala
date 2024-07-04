package controller.state.macroStates

import controller.GameController
import controller.state.AbstractState
import controller.state.turnStates.StartTurnState

import scala.util.control.Breaks.{break, breakable}

class UpdateBarState(controller: GameController) extends AbstractState(controller) {

  override def update(): Unit = {
    controller.turnScheduler.updateMaxBars()
    breakable{
      while(true){
        if(controller.turnScheduler.turn_ready.nonEmpty){
          break()
        }
        controller.turnScheduler.updateActionProgress(controller.actionBarIncrease)
        controller.turnScheduler.checkWaitEntities()
      }
    }
    controller.setState(new StartTurnState(controller))
  }


}
