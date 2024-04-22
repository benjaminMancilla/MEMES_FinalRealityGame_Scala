package turn

import entity.Entity
import collection.mutable

import scala.collection.mutable.ArrayBuffer

class TurnScheduler(var _turn_entities: ArrayBuffer[Entity] = ArrayBuffer.empty[Entity]) extends TraitTurnScheduler {
  val _turn_wait: ArrayBuffer[(Entity, Int, Int, Boolean)] = createTurnInfo
  var _turn_ready: mutable.Queue[(Entity, Int, Int, Boolean)] = mutable.Queue.empty

  def createTurnInfo: ArrayBuffer[(Entity, Int, Int, Boolean)] = {
    val empty_list: ArrayBuffer[(Entity, Int, Int, Boolean)] = ArrayBuffer.empty[(Entity, Int, Int, Boolean)]
    val entity_list: ArrayBuffer[Entity] = turn_entities
    val array_size: Int = turn_entities.size

    for (i <- 0 until array_size) {
      val current_entity: Entity = entity_list(i)
      val max_bar: Int = maxBarValue(current_entity)
      empty_list += ((current_entity, 0, max_bar, false))
    }
    empty_list

  }

  def updateMaxBars(): Unit = {
    val array_size: Int = turn_wait.size
    for (i <- 0 until array_size) {
      val current_entity: Entity = _turn_wait(i)._1
      val max_bar: Int = maxBarValue(current_entity)
      val current_tuple =  (current_entity, _turn_wait(i)._2,  max_bar, _turn_wait(i)._4)
      _turn_wait.update(i, current_tuple)

    }
  }

  def resetAllBarValues(): Unit = {
    val array_size: Int = turn_wait.size
    for (i <- 0 until array_size) {
      val current_entity: Entity = _turn_wait(i)._1
      val current_tuple =  (current_entity, 0, _turn_wait(i)._3, false)
      _turn_wait.update(i, current_tuple)

    }
  }

  def resetBarValue(entity: Entity): Unit = {
    val index = _turn_wait.indexWhere { case (e, _, _, _) => e == entity }
    if (index != -1) {
      val current_tuple = _turn_wait(index).copy(_2 = 0, _4 = false)
      _turn_wait.update(index, current_tuple)
    } else {
    }
  }

  def updateActionProgress(k: Int): Unit = {
    val array_size: Int = turn_wait.size
    for (i <- 0 until array_size) {
      val current_tuple: (Entity, Int, Int, Boolean) = _turn_wait(i)
      val current_max = current_tuple._3
      var current_bar = current_tuple._2
      var boolean_value = false
      current_bar += k
      if (current_bar >= current_max) {
        boolean_value = true
      }

      val updated_tuple = current_tuple.copy(_2 = current_bar, _4 = boolean_value)
      _turn_wait.update(i, updated_tuple)
    }
  }

  def checkWaitEntities(): Unit = {
    val array_size: Int = turn_wait.size
    var ready_entities: ArrayBuffer[(Entity, Int, Int, Boolean)] = ArrayBuffer.empty[(Entity, Int, Int, Boolean)]
    val entitiesToRemove: ArrayBuffer[(Entity, Int, Int, Boolean)] = ArrayBuffer.empty[(Entity, Int, Int, Boolean)]

    for (i <- 0 until array_size) {
      if (_turn_wait(i)._4) {
        val current_max = _turn_wait(i)._3
        val current_bar = _turn_wait(i)._2
        val ready_tuple = _turn_wait(i).copy(_2 = current_bar - current_max, _4 = false)
        entitiesToRemove += _turn_wait(i)  // Agrega la tupla a la lista de entidades a eliminar
        ready_entities.append(ready_tuple)
      }
    }

    for (entityToRemove <- entitiesToRemove) {
      removeWait(entityToRemove)
    }

    ready_entities = ready_entities.sortWith((a, b) => a._2 > b._2)
    for (i <- ready_entities.indices) {
      addReady(ready_entities(i))
    }
  }



  def addEntity(new_char: Entity): Unit =  {
    if (_turn_entities.contains(new_char)) {}
    else {
      _turn_entities.append(new_char)
      addWait((new_char, 0, maxBarValue(new_char), false))
    }
  }

  def removeEntity(old_char: Entity): Unit = {
    if (!_turn_entities.contains(old_char)) {}
    else {
      turn_entities_=(turn_entities.filterInPlace(_ != old_char))
      removeWait((old_char, 0, 0, false))
    }
  }

  def addWait(new_tuple:(Entity, Int, Int, Boolean) ): Unit =  {
    val (new_entity, _, _, _) =new_tuple
    if (!_turn_wait.exists { case (entity, _, _, _) => entity == new_entity }) {
      _turn_wait.append(new_tuple)
    }
  }

  def removeWait(old_tuple:(Entity, Int, Int, Boolean)): Unit = {
    val (old_entity, _, _, _) =old_tuple
    val index = _turn_wait.indexWhere { case (entity, _, _, _) => entity == old_entity }
    if (index != -1) {
      _turn_wait.remove(index)
    }
  }

  def addReady(new_tuple:(Entity, Int, Int, Boolean) ): Unit =  {
    _turn_ready = _turn_ready.enqueue(new_tuple)
  }

  def nextAttacker: (Entity, Int, Int, Boolean) = {
    val next_attacker: (Entity, Int, Int, Boolean) = _turn_ready.dequeue
    next_attacker
  }


  def maxBarValue(entity:Entity): Int = {
    entity.barValue
  }


  def turn_entities: ArrayBuffer[Entity] = _turn_entities
  def turn_entities_=(new_array: ArrayBuffer[Entity]): Unit = {
    _turn_entities = new_array
  }

  def turn_wait: ArrayBuffer[(Entity, Int, Int, Boolean)] = _turn_wait
  def turn_ready: mutable.Queue[(Entity, Int, Int, Boolean)] = _turn_ready



}
