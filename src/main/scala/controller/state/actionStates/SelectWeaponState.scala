package controller.state.actionStates

import controller.GameController
import controller.command.ChangeWeaponGameCommand
import controller.visitor.GameStateVisitor

/**
 * State representing the selection of a weapon for an entity.
 *
 * @param controller The game controller managing the game state.
 */
class SelectWeaponState(controller: GameController) extends AbstractSelectTargetState(controller) {

  // Maximum index of the weapon inventory to select from
  override val maxTargetIndex: Int = controller.weaponInventory.size

  /**
   * Executes the command to change the weapon of the current attacker entity
   * to the selected weapon from the inventory.
   */
  override def update(): Unit = {
    // Check if a weapon has been selected
    if (tryTarget == -1) {
      nextState.foreach(controller.setState)
      return
    }

    // Retrieve current attacker entity and selected weapon
    val entity = controller.turnScheduler.nextAttacker
    val weapon = controller.weaponInventory(tryTarget)

    // Create and execute the change weapon game command
    val weaponCommand = new ChangeWeaponGameCommand(entity, controller, Some(weapon))
    val nextStateOption = weaponCommand.execute()

    // Update affected entity and set the next state
    updateAffectedEntity(tryTarget)
    nextStateOption.foreach(controller.setState)
  }

  /**
   * Accepts a visitor for visiting this state.
   *
   * @param visitor The visitor to accept.
   */
  override def accept(visitor: GameStateVisitor): Unit = {
    visitor.visitWeapon(this)
  }
}

