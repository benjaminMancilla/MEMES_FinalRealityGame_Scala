package controller

import controller.state.{GameState, StartState}
import entity.Entity
import exceptions.Require
import party.Party
import turn.TurnScheduler
import weapon.Weapon

import scala.collection.mutable.ArrayBuffer
class GameControllerConcrete(party: Party, turnSchedulerI: TurnScheduler, actionBarIncreaseI: Int, inventoryI:List[Weapon]) extends GameController{

  val turnScheduler: TurnScheduler = turnSchedulerI
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

  def turnCurrentSate: ArrayBuffer[(Entity, Int, Int, Boolean)] = turnScheduler.turn_info





}
