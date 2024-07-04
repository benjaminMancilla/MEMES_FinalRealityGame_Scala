package controller.command

import controller.GameController
import controller.state.actionStates.{SelectTargetState, SelectWeaponState}
import controller.state.GameState
import controller.state.turnStates.ResetBarState
import exceptions.entityE.ProhibitedTarget
import exceptions.weaponE.EmptyWeaponException
import model.entity.Entity
import model.turn.TurnScheduler

class AttackCommand(attacker: Entity, target: Entity, controller: GameController) extends Command {
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
