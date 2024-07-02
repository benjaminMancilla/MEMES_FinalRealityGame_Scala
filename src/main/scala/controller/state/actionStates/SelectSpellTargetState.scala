package controller.state.actionStates

import controller.GameController
import controller.state.{AbstractState, GameState, ResetBarState}
import entity.Entity
import exceptions.entityE.ProhibitedTarget
import exceptions.magicE.{InvalidMagicType, InvalidSpellTarget, NoMagicPoints, NonMagicWeaponException, NonMagicalCaster}
import exceptions.weaponE.EmptyWeaponException
import magic.Magic
import turn.TurnScheduler

class SelectSpellTargetState(controller: GameController, turnScheduler: TurnScheduler, entity: Entity, spell: Magic)
  extends AbstractSelectTargetState(controller: GameController, turnScheduler: TurnScheduler){


  override def update(): Unit = {
    if (tryTarget == -1) {
      nextState.foreach(controller.setState)
      return
    }
    try {
      controller.turnScheduler.nextAttacker.castSpell(controller.turnScheduler.turn_info(tryTarget)._1, spell)
      updateAffectedEntity()
      nextState = Some(new ResetBarState(controller, turnScheduler, entity))
    } catch {
      case e: InvalidMagicType => //Should not happen due to the implicit entity type
        println(s"Not valid Magic Error: ${e.getMessage}")
        nextState = Some(new ActionState(controller, turnScheduler, entity))

      case e: NonMagicalCaster => //Should not happen due to the implicit entity type
        println(s"Not valid Caster Error: ${e.getMessage}")
        nextState = Some(new ActionState(controller, turnScheduler, entity))

      case e: NoMagicPoints =>
        println(s"Not enough mana: ${e.getMessage}")
        nextState = Some(new ActionState(controller, turnScheduler, entity))

      case e: NonMagicWeaponException =>
        println(s"Must have a magic weapon for the spell: ${e.getMessage}")
        nextState = Some(new ActionState(controller, turnScheduler, entity))

      case e: EmptyWeaponException =>
        println(s"Must have a first weapon for the spell: ${e.getMessage}")
        nextState = Some(new ActionState(controller, turnScheduler, entity))

      case e: InvalidSpellTarget =>
        println(s"You can not cast ${spell.name} on ${turnScheduler.turn_info(tryTarget)._1.name}: ${e.getMessage}")
        nextState = None

    }
    nextState.foreach(controller.setState)
  }

}
