package controller.state.actionStates

import controller.GameController
import controller.state.command.SpellCommand
import magic.Magic

class SelectSpellTargetState(controller: GameController, spell: Magic)
  extends AbstractSelectTargetState(controller: GameController){


  override def update(): Unit = {
    println("SELECTTARGET")
    if (tryTarget == -1) {
      return
    }

    val attacker = controller.turnScheduler.nextAttacker
    val target = controller.turnScheduler.turn_info(tryTarget)._1

    val spellCommand = new SpellCommand(attacker, target, controller, spell)
    val nextStateOption = spellCommand.execute()
    updateAffectedEntity(tryTarget)

    nextStateOption.foreach(controller.setState)
  }

}
