package controller.state

import controller.GameController

class AbstractState(controller: GameController) extends GameState {
  def needInput(): Boolean = false
  def update(): Unit = {}
  def handleInput(input: String): Unit = {}

  protected def updateAffectedEntity(index: Int): Unit = {
    if (!controller.turnScheduler.turn_info(index)._1.state) {
      controller.turnScheduler.removeEntity(controller.turnScheduler.turn_info(index)._1)
    }
  }
}
