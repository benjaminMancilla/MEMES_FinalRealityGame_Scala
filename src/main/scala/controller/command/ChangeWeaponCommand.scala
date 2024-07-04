package controller.command

import controller.GameController
import controller.state.GameState
import controller.state.actionStates.ActionState
import exceptions.weaponE.{InvalidCarrier, InvalidWeaponException}
import model.entity.Entity
import model.weapon.Weapon

class ChangeWeaponCommand(entity: Entity, controller: GameController, weapon: Option[Weapon]) extends Command {
  def execute(): Option[GameState] = {
    try{
      entity.changeWeapon(weapon)
      Some(new ActionState(controller))
    } catch {
      case e: InvalidCarrier =>
        println(s"Weapon Error: ${e.getMessage}") //Should not happen due to implicit entity type
        Some(new ActionState(controller))
      case e: InvalidWeaponException =>
        println(s"${e.getMessage}")
        None
    }
  }
}