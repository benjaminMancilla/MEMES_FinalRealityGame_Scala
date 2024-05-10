package turn

import entity.Entity

trait TraitTurnScheduler extends PITurnScheduler {
  def updateMaxBars(): Unit
  def resetAllBarValues(): Unit
  def resetBarValue(entity: Entity): Unit
  def updateActionProgress(k: Int): Unit
  def checkWaitEntities(): Unit
  def addEntity(new_char: Entity): Unit
  def removeEntity(old_char: Entity): Unit
  def dequeueReady(): Entity





}
