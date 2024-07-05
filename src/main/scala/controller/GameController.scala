package controller
import controller.state.GameState
import model.entity.Entity
import model.turn.TurnScheduler
import model.weapon.Weapon

import scala.collection.mutable.ArrayBuffer

/**
 * Trait representing a game controller responsible for managing game state, input handling,
 * updates, and game mechanics.
 */
trait GameController {
  /**
   * Sets the current game state.
   *
   * @param state The game state to set.
   */
  def setState(state: GameState): Unit

  /**
   * Handles player input to interact with the game.
   *
   * @param input The input command provided by the player.
   */
  def handleInput(input: String): Unit

  /**
   * Updates the game state based on internal logic and player actions.
   */
  def update(): Unit

  /**
   * Gets the increase value for the action bar during turn-based combat.
   */
  def actionBarIncrease: Int

  /**
   * Retrieves the list of weapons available in the player's inventory.
   */
  def weaponInventory: List[Weapon]

  /**
   * Retrieves the current state of all entities in the turn-based scheduler,
   * including their action bar values and states.
   */
  def turnCurrentSate: ArrayBuffer[(Entity, Int, Int, Boolean)]

  /**
   * Retrieves the turn scheduler responsible for managing turn-based actions and order.
   */
  def turnScheduler: TurnScheduler

  /**
   * Retrieves the current overall game state.
   */
  def currentState: GameState

  /**
   * Adds a new weapon to the player's inventory.
   *
   * @param weapon The weapon to add to the inventory.
   */
  def addWeapon(weapon: Weapon): Unit

  /**
   * Retrieves the current result of the combat phase.
   */
  def combatResult: String

  /**
   * Sets the result of the combat phase.
   *
   * @param string The combat result string to set.
   */
  def combatResult_=(string: String): Unit
}

