package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState}
import entity.Entity
import exceptions.weaponE.{InvalidCarrier, InvalidWeaponException}
import turn.TurnScheduler

class SelectWeaponState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity) extends AbstractSelectTargetState(controller, turnScheduler) {

  override val maxTargetIndex: Int = controller.weaponInventory.size
  override def update(): Unit = {
    if (tryTarget == -1) {
      nextState.foreach(controller.setState)
      return
    }
    try{
      controller.turnScheduler.nextAttacker.changeWeapon(Some(controller.weaponInventory(tryTarget)))

    } catch {
      case e: InvalidCarrier =>
        println(s"Weapon Error: ${e.getMessage}") //Should not happen due to implicit entity type
        nextState = Some(new ActionState(controller, turnScheduler, entity))
      case e: InvalidWeaponException =>
        println(s"${e.getMessage}")
        nextState = None
    }
  }
}
