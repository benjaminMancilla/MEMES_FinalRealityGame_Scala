package controller.state.turnStates

import controller.GameController
import controller.state.macroStates.{DefeatState, UpdateBarState, VictoryState}
import controller.state.AbstractState
import controller.visitor.StateVisitor
import model.entity.Entity

class EndTurnState(controller: GameController, endTurnEntity: Entity) extends AbstractState(controller) {
  private val visitor: StateVisitor = new StateVisitor

  override def update(): Unit = {
    if (!endTurnEntity.state){
      controller.turnScheduler.removeEntity(controller.turnScheduler.nextAttacker)
    }
    var characterCounter: Int = 0
    var enemyCounter: Int = 0
    for (tuple <- controller.turnScheduler.turn_info){
      val auxEntity: Entity = tuple._1
      auxEntity.accept(visitor)
      val result: String = visitor.buffer.head
      result match {
        case "E1" => enemyCounter+=1
        case "C1" => characterCounter+=1
        case _ =>
      }
    }
    if (characterCounter == 0){
      controller.setState(new DefeatState(controller))
      return
    }
    if (enemyCounter == 0){
      controller.setState(new VictoryState(controller))
      return
    }
    controller.setState(new UpdateBarState(controller))
  }
}
