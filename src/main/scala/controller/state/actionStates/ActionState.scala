package controller.state.actionStates

import controller.GameController
import controller.state.turnStates.ResetBarState
import controller.state.{AbstractState, GameState}
import controller.visitor.{GameStateVisitor, GeneralActionVisitor}
import model.entity.Entity
import model.turn.TurnScheduler

class ActionState(controller: GameController) extends AbstractState(controller) {
  override def needInput() = true
  private val visitor = new GeneralActionVisitor()
  private var nextState: Option[GameState] = None
  controller.turnScheduler.nextAttacker.accept(visitor)
  private val _validOptions: List[String] = visitor.buffer

  override def handleInput(input: String): Unit = {


    if (validOptions.contains(input)) {
      input match {
        case "Attack" =>
          nextState = Some(new SelectTargetState(controller))
        case "Weapon" =>
          nextState = Some(new SelectWeaponState(controller))
        case "Magic" =>
          nextState = Some(new SelectSpellState(controller))
        case "Pass" =>
          nextState = Some(new ResetBarState(controller))
        case _ =>
        // Handle invalid input if necessary
      }
    }
    else if (validOptions.isEmpty){
      nextState = Some(new SelectTargetState(controller))
    }
    else{
      nextState = Some(this)
    }
  }

  override def update(): Unit = {
    nextState.foreach(controller.setState)
    //If ActionState does not get a valid input nextState stays None.
    //Then, update does nothing, staying in a loop until it gets a valid
    //input.
  }

  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitAction(this)
  }

  def validOptions: List[String] = _validOptions
}
