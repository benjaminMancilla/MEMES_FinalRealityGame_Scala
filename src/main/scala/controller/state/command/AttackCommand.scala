package controller.state.command

import controller.GameController
import controller.state.actionStates.SelectWeaponState
import controller.state.{GameState, ResetBarState}
import entity.Entity
import exceptions.entityE.ProhibitedTarget
import exceptions.weaponE.EmptyWeaponException
import turn.TurnScheduler

class AttackCommand(attacker: Entity, target: Entity, controller: GameController) extends Command {
  def execute(): Option[GameState] = {
    try {
      attacker.doAttack(target)
      println(s"Attack result: ${attacker.doAttack(target)}")
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
        None
    }
  }
}
