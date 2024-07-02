package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState, ResetBarState}
import controller.visitor.{GeneralActionVisitor, SelectSpellVisitor}
import entity.Entity
import exceptions.stateE.InvalidSpellSelector
import magic.Magic
import magic.blackMagic.{Fire, Thunder}
import magic.whiteMagic.{Heal, Paralyze, Poison}
import turn.TurnScheduler

class SelectSpellState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {
  override def needInput() = true
  private val visitor = new SelectSpellVisitor()
  private var nextState: Option[GameState] = None

  override def handleInput(input: String): Unit = {
    try{
      entity.accept(visitor)
    } catch {
      case e: InvalidSpellSelector =>
        nextState = Some(new ActionState(controller, turnScheduler, entity))
    }

    val validOptions: List[String] = visitor.buffer

    if (validOptions.contains(input)) {
      input match {
        case "Fire" =>
          nextState = Some(new SelectSpellTargetState(controller, turnScheduler, entity, new Fire))
        case "Thunder" =>
          nextState = Some(new SelectSpellTargetState(controller, turnScheduler, entity, new Thunder))
        case "Poison" =>
          nextState = Some(new SelectSpellTargetState(controller, turnScheduler, entity, new Poison))
        case "Paralyze" =>
          nextState = Some(new SelectSpellTargetState(controller, turnScheduler, entity, new Paralyze))
        case "Heal" =>
          nextState = Some(new SelectSpellTargetState(controller, turnScheduler, entity, new Heal))
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
}
