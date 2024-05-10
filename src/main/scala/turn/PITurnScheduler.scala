package turn

import entity.Entity

import scala.collection.mutable.ArrayBuffer

trait PITurnScheduler {
  def turn_entities: ArrayBuffer[Entity]
  def turn_wait: ArrayBuffer[Int]
  def turn_ready: ArrayBuffer[(Int, Int)]
  def maxBarValue(entity:Entity): Int
  def nextAttacker: Entity
  def readyEntities(): ArrayBuffer[Entity]

}
