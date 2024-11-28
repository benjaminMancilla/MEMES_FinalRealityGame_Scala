package controller.command

import controller.GameController
import controller.state.GameState
import controller.state.actionStates.ActionState
import controller.state.turnStates.ResetBarState
import exceptions.magicE.{InvalidMagicType, InvalidSpellTarget, NoMagicPoints, NonMagicWeaponException, NonMagicalCaster}
import exceptions.weaponE.EmptyWeaponException
import model.entity.Entity
import model.magic.Magic

/**
 * Command to execute a spell cast by a caster entity on a target entity.
 *
 * @param caster The entity casting the spell.
 * @param target The entity on which the spell is cast.
 * @param controller The game controller managing the game state.
 * @param spell The magic spell being cast.
 */
class SpellGameCommand(caster: Entity, target: Entity, controller: GameController, spell: Magic) extends GameCommand {

  /**
   * Executes the spell casting command, casting the specified spell from the caster entity onto the target entity.
   *
   * @return Optional GameState after executing the spell cast command.
   */
  def execute(): Option[GameState] = {
    try {
      caster.castSpell(target, spell)
      Some(new ResetBarState(controller))
    } catch {
      case e: InvalidMagicType =>
        println(s"Not valid Magic Error: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: NonMagicalCaster =>
        println(s"Not valid Caster Error: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: NoMagicPoints =>
        println(s"Not enough mana: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: NonMagicWeaponException =>
        println(s"Must have a magic weapon for the spell: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: EmptyWeaponException =>
        println(s"Must have a weapon equipped to cast a spell: ${e.getMessage}")
        Some(new ActionState(controller))

      case e: InvalidSpellTarget =>
        println(s"Invalid spell target: ${e.getMessage}")
        None
    }
  }
}

