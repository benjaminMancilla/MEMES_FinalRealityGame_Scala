package controller.state

import controller.GameController
import turn.TurnScheduler

import scala.util.control.Breaks.{break, breakable}

class UpdateBarState(controller: GameController, turnScheduler: TurnScheduler) extends AbstractState {

  override def update(): Unit = {
    turnScheduler.updateMaxBars()
    breakable{
      while(true){
        if(turnScheduler.turn_ready.nonEmpty){
          break()
        }
        turnScheduler.updateActionProgress(controller.actionBarIncrease)
      }
    }
    controller.setState(new StartTurnState(controller, turnScheduler))
  }


}
