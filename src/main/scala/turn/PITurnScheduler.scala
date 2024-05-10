package turn

import entity.Entity

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

trait PITurnScheduler {
  def turn_entities: ArrayBuffer[Entity]
  def turn_wait: ArrayBuffer[(Entity, Int, Int, Boolean)]
  def turn_ready: mutable.Queue[(Entity, Int, Int, Boolean)]
  def maxBarValue(entity:Entity): Int
  def nextAttacker: (Entity, Int, Int, Boolean)

}
