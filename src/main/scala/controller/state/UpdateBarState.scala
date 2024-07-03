package controller.state

import controller.GameController
import turn.TurnScheduler

import scala.util.control.Breaks.{break, breakable}

class UpdateBarState(controller: GameController) extends AbstractState(controller) {

  override def update(): Unit = {
    println("UPDATEBAR")
    controller.turnScheduler.updateMaxBars()
    breakable{
      while(true){
        if(controller.turnScheduler.turn_ready.nonEmpty){
          println("SE DEBEE ROMPEEEEERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR")

          break()
        }
        controller.turnScheduler.updateActionProgress(controller.actionBarIncrease)
        controller.turnScheduler.checkWaitEntities()
      }
    }
    controller.setState(new StartTurnState(controller))
  }


}
