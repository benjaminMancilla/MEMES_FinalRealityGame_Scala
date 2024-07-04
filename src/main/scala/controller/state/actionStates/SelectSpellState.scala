package controller.state.actionStates

import controller.GameController
import controller.state.turnStates.ResetBarState
import controller.state.{AbstractState, GameState}
import controller.visitor.{GameStateVisitor, GeneralActionVisitor, SelectSpellVisitor}
import exceptions.stateE.InvalidSpellSelector
import model.entity.Entity
import model.magic.Magic
import model.magic.blackMagic.{Fire, Thunder}
import model.magic.whiteMagic.{Heal, Paralyze, Poison}
import model.turn.TurnScheduler

class SelectSpellState(controller: GameController) extends AbstractState(controller) {
  override def needInput() = true
  private val visitor = new SelectSpellVisitor()
  private var nextState: Option[GameState] = None

  override def handleInput(input: String): Unit = {
    try{
      controller.turnScheduler.nextAttacker.accept(visitor)
    } catch {
      case e: InvalidSpellSelector =>
        nextState = Some(new ActionState(controller))
    }

    val validOptions: List[String] = visitor.buffer

    if (validOptions.contains(input)) {
      input match {
        case "Fire" =>
          nextState = Some(new SelectSpellTargetState(controller, new Fire))
        case "Thunder" =>
          nextState = Some(new SelectSpellTargetState(controller, new Thunder))
        case "Poison" =>
          nextState = Some(new SelectSpellTargetState(controller, new Poison))
        case "Paralyze" =>
          nextState = Some(new SelectSpellTargetState(controller, new Paralyze))
        case "Heal" =>
          nextState = Some(new SelectSpellTargetState(controller, new Heal))
        case _ =>
        // Handle invalid input if necessary
      }
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

  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitSpell(this)
  }
}
