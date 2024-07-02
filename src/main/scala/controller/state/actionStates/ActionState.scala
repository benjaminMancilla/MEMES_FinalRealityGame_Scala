package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState, ResetBarState}
import controller.visitor.GeneralActionVisitor
import entity.Entity
import turn.TurnScheduler

class ActionState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {
  override def needInput() = true
  private val visitor = new GeneralActionVisitor()
  private var nextState: Option[GameState] = None

  override def handleInput(input: String): Unit = {
    controller.turnScheduler.nextAttacker.accept(visitor)
    val validOptions: List[String] = visitor.buffer

    if (validOptions.contains(input)) {
      input match {
        case "Attack" =>
          nextState = Some(new SelectTargetState(controller, turnScheduler, entity))
        case "Weapon" =>
          nextState = Some(new SelectWeaponState(controller, turnScheduler, entity))
        case "Magic" =>
          nextState = Some(new SelectSpellState(controller, turnScheduler, entity))
        case "Pass" =>
          nextState = Some(new ResetBarState(controller, turnScheduler, entity))
        case _ =>
        // Handle invalid input if necessary
      }
    }
    else if (validOptions.isEmpty){
      nextState = Some(new SelectTargetState(controller, turnScheduler, entity))
    }
    else{
      // nextState stays empty
    }
  }

  override def update(): Unit = {
    nextState.foreach(controller.setState)
    //If ActionState does not get a valid input nextState stays None.
    //Then, update does nothing, staying in a loop until it gets a valid
    //input.
  }
}
