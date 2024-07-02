package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState, ResetBarState}
import entity.Entity
import exceptions.entityE.ProhibitedTarget
import exceptions.weaponE.EmptyWeaponException
import turn.TurnScheduler

class SelectTargetState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractState {

  private val maxTargetIndex: Int = turnScheduler.turn_info.size
  private var nextState: Option[GameState] = None
  private var tryTarget: Int = -1
  override def handleInput(input: String): Unit = {
    try {
      val number = input.toInt
      if (number >= 0 && number <= maxTargetIndex) {
        tryTarget = number
      }
      else {// Invalid Target
        }
    } catch {
      case e: NumberFormatException =>
        //Pon un numero po tontin

    }
  }

  override def update(): Unit = {
    if (tryTarget == -1) {
      nextState.foreach(controller.setState)
      return
    }
    try {
      entity.doAttack(turnScheduler.turn_info(tryTarget)._1)
      if (!turnScheduler.turn_info(tryTarget)._1.state) {
        turnScheduler.removeEntity(turnScheduler.turn_info(tryTarget)._1)
      }
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
