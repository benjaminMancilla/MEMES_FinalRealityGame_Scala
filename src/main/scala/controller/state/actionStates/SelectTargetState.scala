package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState, ResetBarState}
import entity.Entity
import exceptions.entityE.ProhibitedTarget
import exceptions.weaponE.EmptyWeaponException
import turn.TurnScheduler

class SelectTargetState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity)
  extends AbstractSelectTargetState(turnScheduler: TurnScheduler) {

  override def update(): Unit = {
    if (tryTarget == -1) {
      nextState.foreach(controller.setState)
      return
    }
    try {
      entity.doAttack(turnScheduler.turn_info(tryTarget)._1)
      updateAffectedEntity()
      nextState = Some(new ResetBarState(controller, turnScheduler, entity))
    } catch {
      case e: EmptyWeaponException =>
        println(s"EmptyWeaponException: ${e.getMessage}")
        println("Equip a weapon if you want to attack!")
        nextState = Some(new SelectWeaponState(controller, turnScheduler, entity))


      case e: ProhibitedTarget =>
        println(s"ProhibitedTarget: ${e.getMessage}")
        println("You can not attack your team!")
    }
    nextState.foreach(controller.setState)
  }
}
