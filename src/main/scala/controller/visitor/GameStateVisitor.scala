package controller.visitor

import controller.state.GameState
import controller.state.actionStates.{ActionState, SelectSpellState, SelectWeaponState}

trait GameStateVisitor {
  def visitAction(state: ActionState): Unit
  def visitSpell(state: SelectSpellState): Unit
  def visitWeapon(state: SelectWeaponState): Unit
  def visitTarget(state: GameState): Unit
  def visitNotSelectState(state: GameState): Unit


}
