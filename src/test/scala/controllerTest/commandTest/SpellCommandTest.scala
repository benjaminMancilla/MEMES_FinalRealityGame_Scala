package controllerTest.commandTest

import controller.GameController
import controller.state.ResetBarState
import controller.state.actionStates.ActionState
import controller.state.command.SpellCommand
import controllerTest.ControllerTest
import entity.character.magicCharacter.MagicCharacter
import magic.blackMagic.Fire
import magic.whiteMagic.Heal
import weapon.commonWeapon.Sword

class SpellCommandTest extends ControllerTest {
  test("SpellCommand should transition to ResetBarState when spell is cast successfully") {
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire
    val spellCommand = new SpellCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ResetBarState])
  }

  test("SpellCommand should transition to ActionState on InvalidMagicType exception") {
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val invalidSpell = new Heal
    val spellCommand = new SpellCommand(caster, target, controller, invalidSpell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellCommand should transition to ActionState on NonMagicalCaster exception") {
    val caster = controller.turnScheduler.turn_info(0)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire

    val spellCommand = new SpellCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellCommand should transition to ActionState on NoMagicPoints exception") {
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire
    caster.asInstanceOf[MagicCharacter].currentMagicPoints = 0


    val spellCommand = new SpellCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellCommand should transition to ActionState on NonMagicWeaponException") {
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire
    caster.changeWeapon(Some(new Sword("Sword", 10, 10)))

    val spellCommand = new SpellCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellCommand should return None on InvalidSpellTarget exception") {
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(0)._1
    val spell = new Fire

    val spellCommand = new SpellCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isEmpty)
  }
}
