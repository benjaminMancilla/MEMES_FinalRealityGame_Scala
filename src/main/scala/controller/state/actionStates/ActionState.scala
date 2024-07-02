package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState, ResetBarState}
import controller.visitor.GeneralActionVisitor
import entity.Entity
import turn.TurnScheduler

class ActionState(controller: GameController) extends AbstractState {
  override def needInput() = true
  private val visitor = new GeneralActionVisitor()
  private var nextState: Option[GameState] = None

  override def handleInput(input: String): Unit = {
    println("ACTIONSTATE")
    controller.turnScheduler.nextAttacker.accept(visitor)
    val validOptions: List[String] = visitor.buffer
    println(validOptions)
    println(input)
    println(validOptions.contains(input))

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
}
