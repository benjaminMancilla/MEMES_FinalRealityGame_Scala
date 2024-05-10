package turn

import entity.Entity
import scala.collection.mutable.ArrayBuffer

trait TraitTurnScheduler extends PITurnScheduler {
  def createTurnInfo: ArrayBuffer[(Entity, Int, Int, Boolean)]
  def updateMaxBars(): Unit
  def resetAllBarValues(): Unit
  def resetBarValue(entity: Entity): Unit
  def updateActionProgress(k: Int): Unit
  def checkWaitEntities(): Unit
  def addEntity(new_char: Entity): Unit
  def removeEntity(old_char: Entity): Unit
  def addWait(new_tuple:(Entity, Int, Int, Boolean) ): Unit
  def removeWait(old_tuple:(Entity, Int, Int, Boolean)): Unit
  def addReady(new_tuple:(Entity, Int, Int, Boolean) ): Unit

  def turn_entities_=(new_party: ArrayBuffer[Entity]): Unit



}
