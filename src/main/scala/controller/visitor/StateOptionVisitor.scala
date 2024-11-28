package controller.visitor

import controller.state.GameState
import controller.state.actionStates.{ActionState, SelectSpellState, SelectWeaponState}
import exceptions.stateE.InvalidSpellSelector
import vista.GameView

/**
 * Implementation of GameStateVisitor that visits different game states to display corresponding options in the game view.
 *
 * @param view The GameView instance responsible for displaying game options.
 */
class StateOptionVisitor(view: GameView) extends GameStateVisitor {

  /**
   * Displays spell options for the SelectSpellState by querying the next attacker's available spells.
   * If the attacker does not have spells, displays an error message.
   *
   * @param state The SelectSpellState being visited.
   */
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

  /**
   * Displays weapon options for the SelectWeaponState based on the controller's weapon inventory.
   *
   * @param state The SelectWeaponState being visited.
   */
  override def visitWeapon(state: SelectWeaponState): Unit = {
    view.displayOptionWeapon(state.getController.weaponInventory)
  }

  /**
   * Displays target options for the GameState based on entities other than the next attacker.
   *
   * @param state The current GameState being visited.
   */
  override def visitTarget(state: GameState): Unit = {
    val potentialTargets = state.getController.turnScheduler.turn_info
      .filter(_._1 != state.getController.turnScheduler.nextAttacker)
      .map(_._1.name)
    view.displayOptionTarget(potentialTargets.toList)
  }

  /**
   * Displays common action options for the ActionState.
   *
   * @param state The ActionState being visited.
   */
  override def visitAction(state: ActionState): Unit = {
    val options = state.validOptions
    view.displayOptionCommon(options)
  }

  /**
   * Placeholder method for visiting states that do not require specific options to be displayed.
   *
   * @param state The GameState being visited, which does not require option display.
   */
  override def visitNotSelectState(state: GameState): Unit = {}

}
