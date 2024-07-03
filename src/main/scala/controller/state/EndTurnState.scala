package controller.state

import controller.GameController
import controller.visitor.StateVisitor
import entity.Entity
import turn.TurnScheduler

class EndTurnState(controller: GameController) extends AbstractState(controller) {
  private val visitor: StateVisitor = new StateVisitor

  override def update(): Unit = {
    println("END")

    val endTurnEntity = controller.turnScheduler.nextAttacker
    controller.turnScheduler.dequeueReady()
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
