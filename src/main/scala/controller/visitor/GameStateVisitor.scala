package controller.visitor

import controller.state.GameState
import controller.state.actionStates.{ActionState, SelectSpellState, SelectWeaponState}

/**
 * Trait defining a visitor pattern for visiting different states of the game.
 * Each method corresponds to visiting a specific state subclass implementing GameState.
 */
trait GameStateVisitor {

  /**
   * Visits an ActionState and performs specific actions associated with this state.
   *
   * @param state The ActionState to visit.
   */
  def visitAction(state: ActionState): Unit

  /**
   * Visits a SelectSpellState and performs specific actions associated with this state.
   *
   * @param state The SelectSpellState to visit.
   */
  def visitSpell(state: SelectSpellState): Unit

  /**
   * Visits a SelectWeaponState and performs specific actions associated with this state.
   *
   * @param state The SelectWeaponState to visit.
   */
  def visitWeapon(state: SelectWeaponState): Unit

  /**
   * Visits a GameState where a target is selected and performs specific actions associated with this state.
   *
   * @param state The GameState representing the target selection state to visit.
   */
  def visitTarget(state: GameState): Unit

  /**
   * Visits a GameState that is not in a selection state and performs specific actions associated with this state.
   *
   * @param state The GameState to visit that is not in a selection state.
   */
  def visitNotSelectState(state: GameState): Unit
}

