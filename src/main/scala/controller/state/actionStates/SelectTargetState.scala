package controller.state.actionStates

import controller.GameController
import controller.state.GameState
import entity.Entity
import turn.TurnScheduler

class SelectTargetState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends GameState {
  private val visitor = new GeneralActionVisitor()
  private var nextState: Option[GameState] = None

  override def handleInput(input: String): Unit = {
    entity.accept(visitor)
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
  }

  override def update(): Unit = {
    nextState.foreach(controller.setState)
    //If ActionState does not get a valid input nextState stays None.
    //Then, update does nothing, staying in a loop until it gets a valid
    //input.
  }
}
