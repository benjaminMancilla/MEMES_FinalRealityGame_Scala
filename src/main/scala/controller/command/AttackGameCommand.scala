package controller.command

import controller.GameController
import controller.state.actionStates.{SelectTargetState, SelectWeaponState}
import controller.state.GameState
import controller.state.turnStates.ResetBarState
import exceptions.entityE.ProhibitedTarget
import exceptions.weaponE.EmptyWeaponException
import model.entity.Entity
import model.turn.TurnScheduler

/**
 * Command to execute an attack action between an attacker and a target entity.
 *
 * @param attacker The entity performing the attack.
 * @param target The entity being targeted by the attack.
 * @param controller The game controller managing the game state.
 */
class AttackGameCommand(attacker: Entity, target: Entity, controller: GameController) extends GameCommand {

  /**
   * Executes the attack command, updating entities and returning a new game state.
   *
   * @return Optional GameState after executing the attack command.
   */
  def execute(): Option[GameState] = {
    try {
      attacker.doGenericAttack(target)
      controller.turnScheduler.updateEntity(attacker)
      controller.turnScheduler.updateEntity(target)
      Some(new ResetBarState(controller))
    } catch {
      case e: EmptyWeaponException =>
        println(s"EmptyWeaponException: ${e.getMessage}")
        println("Equip a weapon if you want to attack!")
        Some(new SelectWeaponState(controller))

      case e: ProhibitedTarget =>
        println(s"ProhibitedTarget: ${e.getMessage}")
        println("You cannot attack your team!")
        Some(new SelectTargetState(controller))
    }
  }
}

