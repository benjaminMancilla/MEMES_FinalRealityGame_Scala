package controller.visitor

import controller.state.GameState
import controller.state.actionStates.{ActionState, SelectSpellState, SelectWeaponState}
import exceptions.stateE.InvalidSpellSelector
import vista.GameView

class StateOptionVisitor(view: GameView) extends GameStateVisitor {

  override def visitSpell(state: SelectSpellState): Unit = {
    val attacker = state.getController.turnScheduler.nextAttacker
    val selectSpellVisitor = new SelectSpellVisitor()

    try {
      attacker.accept(selectSpellVisitor)
      view.displayOptionSpell(selectSpellVisitor.buffer)
    } catch {
      case e: InvalidSpellSelector =>
        view.displayOptionCommon(List(e.getMessage))
    }
  }

  override def visitWeapon(state: SelectWeaponState): Unit = {
    view.displayOptionWeapon(state.getController.weaponInventory)
  }

  override def visitTarget(state: GameState): Unit = {
    val potentialTargets = state.getController.turnScheduler.turn_info.filter(_._1 != state.getController.turnScheduler.nextAttacker).map(_._1.name)
    view.displayOptionTarget(potentialTargets.toList)
  }

  override def visitAction(state: ActionState): Unit = {
    val options = state.validOptions
    view.displayOptionCommon(options)
  }

  def visitNotSelectState(state: GameState): Unit = {}

}