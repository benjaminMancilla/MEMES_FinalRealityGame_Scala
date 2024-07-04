package controller.command

import controller.GameController
import controller.state.GameState
import controller.state.actionStates.ActionState
import controller.state.turnStates.ResetBarState
import exceptions.magicE.{InvalidMagicType, InvalidSpellTarget, NoMagicPoints, NonMagicWeaponException, NonMagicalCaster}
import exceptions.weaponE.EmptyWeaponException
import model.entity.Entity
import model.magic.Magic

class SpellCommand(caster: Entity, target: Entity, controller: GameController, spell: Magic) extends Command {
  def execute(): Option[GameState] = {
    try {
      caster.castSpell(target, spell)
      //updateAffectedEntity()
      Some(new ResetBarState(controller))
    } catch {
      case e: InvalidMagicType => //Should not happen due to the implicit entity type
        println(s"Not valid Magic Error: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: NonMagicalCaster => //Should not happen due to the implicit entity type
        println(s"Not valid Caster Error: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: NoMagicPoints =>
        println(s"Not enough mana: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: NonMagicWeaponException =>
        println(s"Must have a magic weapon for the spell: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: EmptyWeaponException =>
        println(s"Must have a first weapon for the spell: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: InvalidSpellTarget =>
        println(s"You can not cast ${spell.name} on ${target.name}: ${e.getMessage}")
        None
    }

  }


}
