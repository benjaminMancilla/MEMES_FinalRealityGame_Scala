package controller

import controller.state.{GameState, StartState}
import exceptions.Require
import party.Party
import turn.TurnScheduler
import weapon.Weapon
class GameControllerConcrete(party: Party, turnScheduler: TurnScheduler, actionBarIncreaseI: Int, inventoryI:List[Weapon]) extends GameController{

  private val _inventory: List[Weapon] = Require.WeaponInventory(inventoryI, party, "Inventory").sizeAtLeast()
  private var currentState: GameState = new StartState(this, turnScheduler)
  private val _actionBarIncrease = {Require.Stat(actionBarIncreaseI, "actionBarIncrease") atLeast 1 }

  def setState(state: GameState): Unit = {
    currentState = state
  }

  def handleInput(input: String): Unit = {
    currentState.handleInput(input)
  }

  def update(): Unit = {
    currentState.update()
  }

  def actionBarIncrease: Int = _actionBarIncrease

  def weaponInventory: List[Weapon] = _inventory




}
