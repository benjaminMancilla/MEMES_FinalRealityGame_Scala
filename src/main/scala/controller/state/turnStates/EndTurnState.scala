package controller.state.turnStates

import controller.GameController
import controller.state.macroStates.{DefeatState, UpdateBarState, VictoryState}
import controller.state.AbstractState
import controller.visitor.StateVisitor
import model.entity.Entity

/**
 * State representing the end of a turn in the game.
 *
 * @param controller    The game controller managing the game state.
 * @param endTurnEntity The entity that just completed its turn.
 */
class EndTurnState(controller: GameController, endTurnEntity: Entity) extends AbstractState(controller) {
  // Visitor used to determine the type of entities in the turn scheduler
  private val visitor: StateVisitor = new StateVisitor

  /**
   * Updates the game state based on the completion of the current turn.
   * Checks the state of all entities in the turn scheduler and transitions to the appropriate state:
   * - Removes entities that are no longer active.
   * - Counts remaining characters and enemies.
   * - Transitions to DefeatState if no characters remain.
   * - Transitions to VictoryState if no enemies remain.
   * - Transitions to UpdateBarState to continue the game if both characters and enemies remain.
   */
  override def update(): Unit = {
    // Remove the entity that just completed its turn if it is no longer active
    if (!endTurnEntity.state){
      controller.turnScheduler.removeEntity(controller.turnScheduler.nextAttacker)
    }

    // Counters to track the number of characters and enemies
    var characterCounter: Int = 0
    var enemyCounter: Int = 0

    // Iterate through all entities in the turn scheduler
    for (tuple <- controller.turnScheduler.turn_info){
      val auxEntity: Entity = tuple._1
      auxEntity.accept(visitor)
      val result: String = visitor.buffer.head

      // Increment counters based on entity type
      result match {
        case "E1" => enemyCounter += 1
        case "C1" => characterCounter += 1
        case _ =>
      }
    }

    // Transition to DefeatState if no characters remain
    if (characterCounter == 0){
      controller.setState(new DefeatState(controller))
      return
    }

    // Transition to VictoryState if no enemies remain
    if (enemyCounter == 0){
      controller.setState(new VictoryState(controller))
      return
    }

    // Transition to UpdateBarState to continue the game if both characters and enemies remain
    controller.setState(new UpdateBarState(controller))
  }
}

