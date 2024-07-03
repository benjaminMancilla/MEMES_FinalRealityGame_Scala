package controller

import controller.state.{GameState, StartState}
import entity.Entity
import exceptions.Require
import party.Party
import turn.TurnScheduler
import weapon.Weapon

import scala.collection.mutable.ArrayBuffer
class GameControllerConcrete(partyI: Party, turnSchedulerI: TurnScheduler, actionBarIncreaseI: Int, inventoryI:List[Weapon]) extends GameController{
  val party: Party = partyI
  val turnScheduler: TurnScheduler = turnSchedulerI
  private var _inventory: List[Weapon] = Require.WeaponInventory(inventoryI, party, "Inventory").sizeAtLeast()
  private var _currentState: GameState = new StartState(this)
  private val _actionBarIncrease = {Require.Stat(actionBarIncreaseI, "actionBarIncrease") atLeast 1 }

  def setState(state: GameState): Unit = {
    _currentState = state
  }

  def handleInput(input: String): Unit = {
    _currentState.handleInput(input)
  }

  def update(): Unit = {
    _currentState.update()
  }

  def actionBarIncrease: Int = _actionBarIncrease

  def weaponInventory: List[Weapon] = _inventory

  def turnCurrentSate: ArrayBuffer[(Entity, Int, Int, Boolean)] = turnScheduler.turn_info

  def currentState: GameState = _currentState

  def addWeapon(weapon: Weapon): Unit = {
    val newList = _inventory :+ weapon
    _inventory = newList
  }


}
