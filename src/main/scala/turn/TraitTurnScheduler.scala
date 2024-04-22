package turn

import entity.Entity
import collection.mutable

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

trait TraitTurnScheduler {
  var _turn_entities: ArrayBuffer[Entity]
  val _turn_wait: ArrayBuffer[(Entity, Int, Int, Boolean)]
  var _turn_ready: mutable.Queue[(Entity, Int, Int, Boolean)]

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
  def nextAttacker: (Entity, Int, Int, Boolean)
  def maxBarValue(entity:Entity): Int


  def turn_entities: ArrayBuffer[Entity]
  def turn_entities_=(new_party: ArrayBuffer[Entity]): Unit

  def turn_wait: ArrayBuffer[(Entity, Int, Int, Boolean)]
  def turn_ready: mutable.Queue[(Entity, Int, Int, Boolean)]


}
