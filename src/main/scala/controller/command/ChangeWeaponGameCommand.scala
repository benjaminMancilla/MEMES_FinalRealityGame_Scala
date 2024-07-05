package controller.command

import controller.GameController
import controller.state.GameState
import controller.state.actionStates.ActionState
import exceptions.weaponE.{InvalidCarrier, InvalidWeaponException}
import model.entity.Entity
import model.weapon.Weapon

/**
 * Command to execute a weapon change action for an entity.
 *
 * @param entity The entity performing the weapon change.
 * @param controller The game controller managing the game state.
 * @param weapon Optional new weapon to equip. If None, it indicates an attempt to unequip the current weapon.
 */
class ChangeWeaponGameCommand(entity: Entity, controller: GameController, weapon: Option[Weapon]) extends GameCommand {

  /**
   * Executes the weapon change command, updating the entity's weapon and returning a new game state.
   *
   * @return Optional GameState after executing the weapon change command.
   */
  def execute(): Option[GameState] = {
    try {
      entity.changeWeapon(weapon)
      Some(new ActionState(controller))
    } catch {
      case e: InvalidCarrier =>
        println(s"Weapon Error: ${e.getMessage}") // Should not happen due to implicit entity type handling
        Some(new ActionState(controller))
      case e: InvalidWeaponException =>
        println(s"${e.getMessage}")
        None
    }
  }
}

