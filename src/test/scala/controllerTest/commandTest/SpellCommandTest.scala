package controllerTest.commandTest

import controller.GameController
import controller.command.SpellGameCommand
import controller.state.actionStates.ActionState
import controller.state.turnStates.ResetBarState
import controllerTest.ControllerGenerator
import model.entity.character.magicCharacter.MagicCharacter
import model.magic.blackMagic.Fire
import model.magic.whiteMagic.Heal
import model.weapon.commonWeapon.Sword

class SpellCommandTest extends ControllerGenerator {
  test("SpellGameCommand should transition to ResetBarState when spell is cast successfully") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire
    val spellCommand = new SpellGameCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ResetBarState])
  }

  test("SpellGameCommand should transition to ActionState on InvalidMagicType exception") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val invalidSpell = new Heal
    val spellCommand = new SpellGameCommand(caster, target, controller, invalidSpell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellGameCommand should transition to ActionState on NonMagicalCaster exception") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()
    val caster = controller.turnScheduler.turn_info(0)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire

    val spellCommand = new SpellGameCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellGameCommand should transition to ActionState on NoMagicPoints exception") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire
    caster.asInstanceOf[MagicCharacter].currentMagicPoints = 0


    val spellCommand = new SpellGameCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellGameCommand should transition to ActionState on NonMagicWeaponException") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(4)._1
    val spell = new Fire
    caster.changeWeapon(Some(new Sword("Sword", 10, 10)))

    val spellCommand = new SpellGameCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isDefined)
    assert(nextStateOption.get.isInstanceOf[ActionState])
  }

  test("SpellGameCommand should return None on InvalidSpellTarget exception") {
    val caster = controller.turnScheduler.turn_info(1)._1
    val target = controller.turnScheduler.turn_info(0)._1
    val spell = new Fire

    val spellCommand = new SpellGameCommand(caster, target, controller, spell)

    val nextStateOption = spellCommand.execute()
    assert(nextStateOption.isEmpty)
  }
}
