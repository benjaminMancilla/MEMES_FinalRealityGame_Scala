package controller

import controller.state.GameState
import controller.state.macroStates.StartState
import exceptions.Require
import model.entity.Entity
import model.party.Party
import model.turn.TurnScheduler
import model.weapon.Weapon
import scala.collection.mutable.ArrayBuffer

/**
 * Concrete implementation of a game controller responsible for managing game state,
 * player party, turn-based combat mechanics, and inventory.
 *
 * @param partyI The initial party configuration.
 * @param turnSchedulerI The turn scheduler managing combat turns.
 * @param actionBarIncreaseI The increase value for the action bar during turn-based combat.
 * @param inventoryI The initial list of weapons in the player's inventory.
 */
class GameControllerConcrete(partyI: Party, turnSchedulerI: TurnScheduler, actionBarIncreaseI: Int, inventoryI: List[Weapon])
  extends GameController {

  /**
   * The party managed by the game controller.
   */
  val party: Party = partyI

  /**
   * The turn scheduler responsible for managing turn-based actions and order.
   */
  val turnScheduler: TurnScheduler = turnSchedulerI

  /**
   * The player's inventory of weapons.
   */
  private var _inventory: List[Weapon] = Require.WeaponInventory(inventoryI, party, "Inventory").sizeAtLeast()

  /**
   * The current game state.
   */
  private var _currentState: GameState = new StartState(this)

  /**
   * The increase value for the action bar during turn-based combat.
   */
  private val _actionBarIncrease = { Require.Stat(actionBarIncreaseI, "actionBarIncrease") atLeast 1 }

  /**
   * The current result of the combat phase.
   */
  private var _combatResult: String = "Unfinished"

  /**
   * Sets the current game state.
   *
   * @param state The game state to set.
   */
  def setState(state: GameState): Unit = {
    _currentState = state
  }

  /**
   * Handles player input to interact with the game.
   *
   * @param input The input command provided by the player.
   */
  def handleInput(input: String): Unit = {
    _currentState.handleInput(input)
  }

  /**
   * Updates the game state based on internal logic and player actions.
   */
  def update(): Unit = {
    _currentState.update()
  }

  /**
   * Retrieves the increase value for the action bar during turn-based combat.
   *
   * @return The action bar increase value.
   */
  def actionBarIncrease: Int = _actionBarIncrease

  /**
   * Retrieves the list of weapons available in the player's inventory.
   *
   * @return The list of weapons.
   */
  def weaponInventory: List[Weapon] = _inventory

  /**
   * Retrieves the current state of all entities in the turn-based scheduler,
   * including their action bar values and states.
   *
   * @return The current state of all entities.
   */
  def turnCurrentSate: ArrayBuffer[(Entity, Int, Int, Boolean)] = turnScheduler.turn_info

  /**
   * Retrieves the current overall game state.
   *
   * @return The current game state.
   */
  def currentState: GameState = _currentState

  /**
   * Adds a new weapon to the player's inventory.
   *
   * @param weapon The weapon to add to the inventory.
   */
  def addWeapon(weapon: Weapon): Unit = {
    _inventory = _inventory :+ weapon
  }

  /**
   * Retrieves the current result of the combat phase.
   *
   * @return The current combat result.
   */
  def combatResult: String = _combatResult

  /**
   * Sets the result of the combat phase.
   *
   * @param string The combat result string to set.
   */
  def combatResult_=(string: String): Unit = {
    _combatResult = string
  }
}

