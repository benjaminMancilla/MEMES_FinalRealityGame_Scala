package turn

import entity.Entity

import scala.collection.mutable.ArrayBuffer
//PriorityQueue no deja quitar un elemento que no sea el primero, por lo que prefiero usar mi propia cola
class TurnScheduler(turn_entitiesI: ArrayBuffer[Entity]) extends TraitTurnScheduler {
  private val _turn_info : ArrayBuffer[(Entity, Int, Int, Boolean)] = createTurnInfo(turn_entitiesI)
  checkEntitiesRestrictions()
  private var _turn_wait: ArrayBuffer[Int] = createTurnWaitIndex(turn_entitiesI.size)
  private val _turn_ready: ArrayBuffer[(Int, Int)] = ArrayBuffer.empty

  private def checkEntitiesRestrictions(): Unit = {
    if (_turn_info.size > 8) {
      throw new IllegalArgumentException("Max number of entities is exceeded.")
    } else if (_turn_info.size < 4) {
      throw new IllegalArgumentException("Min number of entities is exceeded.")
    } else {
      var characterCounter: Int = 0
      for (tuple <- _turn_info) {
        if (tuple._1.isPlayer) {
          characterCounter+=1
        }
      }
      if (characterCounter != 3) {
        throw new IllegalArgumentException("Max number of characters (3) is exceeded.")
      }
    }
  }

  private def updateIndex(removedIndex: Int): Unit = {
    _turn_wait.filterInPlace(_ != removedIndex)
    _turn_ready.filterInPlace(tuple => tuple._1 != removedIndex)
    for (i <- _turn_wait.indices){
      if (_turn_wait(i) > removedIndex) {
        _turn_wait(i) -= 1
      }
    }
    for (j <- _turn_ready.indices) {
      if(_turn_ready(j)._1 > removedIndex){
        var tuple_copy = _turn_ready(j)
        val newInt = tuple_copy._1 - 1
        tuple_copy = tuple_copy.copy(_1 = newInt)
        _turn_ready.update(j, tuple_copy)
      }
    }
  }

  private def searchIndexTurnReady(lookedValue: Int): Int = {
    var index: Int = 0
    val turnReadySize = _turn_ready.size
    while(index < turnReadySize) {
      if (_turn_ready(index)._1 == lookedValue) {
        return index
      }
      index+=1
    }
    -1
  }

  private def createTurnWaitIndex(n: Int): ArrayBuffer[Int] = {
    val arrayBuffer = ArrayBuffer[Int]()
    for (i <- 0 until n) {
      arrayBuffer += i
    }
    arrayBuffer
  }

  private def createTurnInfo(turn_entities: ArrayBuffer[Entity]): ArrayBuffer[(Entity, Int, Int, Boolean)] = {
    val tuples_list: ArrayBuffer[(Entity, Int, Int, Boolean)] = ArrayBuffer.empty[(Entity, Int, Int, Boolean)]
    val entity_list: ArrayBuffer[Entity] = turn_entities
    val array_size: Int = turn_entities.size

    for (i <- 0 until array_size) {
      val current_entity: Entity = entity_list(i)
      val max_bar: Int = maxBarValue(current_entity)
      tuples_list += ((current_entity, 0, max_bar, false))
    }
    tuples_list

  }

  def updateMaxBars(): Unit = {
    for (i <- _turn_wait) {
      val current_entity: Entity = _turn_info(i)._1
      val max_bar: Int = maxBarValue(current_entity)
      val current_tuple =  (current_entity, _turn_info(i)._2,  max_bar, _turn_info(i)._4)
      _turn_info.update(i, current_tuple)

    }
  }

  def resetAllBarValues(): Unit = {
    for (i <- _turn_info.indices) {
      val current_entity: Entity = _turn_info(i)._1
      val current_tuple =  (current_entity, 0, _turn_info(i)._3, false)
      _turn_info.update(i, current_tuple)

    }
    for (tuple <- _turn_ready) {
      _turn_wait += tuple._1
    }
    _turn_ready.clear()
  }

  def resetBarValue(entity: Entity): Unit = {
    val index = _turn_info.indexWhere { case (e, _, _, _) => e == entity }
    if (index != -1) {
      val current_tuple = _turn_info(index).copy(_2 = 0, _4 = false)
      _turn_info.update(index, current_tuple)
      val readyIndex: Int = searchIndexTurnReady(index)
      if (readyIndex != -1) {
        _turn_ready.remove(readyIndex)
        _turn_wait += index
      }
    }
  }

  def updateActionProgress(k: Int): Unit = {
    for (i <- _turn_wait) {
      val current_tuple: (Entity, Int, Int, Boolean) = _turn_info(i)
      val current_max = current_tuple._3
      var current_bar = current_tuple._2
      var boolean_value = false
      current_bar += k
      if (current_bar >= current_max) {
        boolean_value = true
      }
      val updated_tuple = current_tuple.copy(_2 = current_bar, _4 = boolean_value)
      _turn_info.update(i, updated_tuple)
    }
  }

  def checkWaitEntities(): Unit = {
    val _turn_wait_copy = _turn_wait.toList
    for (i <- _turn_wait_copy) {
      if (_turn_info(i)._4) {
        val current_max = _turn_info(i)._3
        val current_bar = _turn_info(i)._2
        val ready_tuple = _turn_info(i).copy(_2 = current_bar - current_max, _4 = true)
        _turn_wait -= i
        addReady((i, current_bar - current_max))
        _turn_info.update(i, ready_tuple)
      }
    }
  }

  def readyEntities(): ArrayBuffer[Entity] = {
    val readyEntities: ArrayBuffer[Entity] = ArrayBuffer.empty[Entity]
    for (tuple <- _turn_ready) {
      readyEntities += _turn_info(tuple._1)._1
    }
    readyEntities
  }

  def addEntity(new_char: Entity): Unit =  {
    if (_turn_info.exists(tuple => tuple._1 == new_char)) {}
    else {
      _turn_info += ((new_char, 0, maxBarValue(new_char), false))
      _turn_wait += _turn_info.size-1
    }
    checkEntitiesRestrictions()
  }

  def removeEntity(old_char: Entity): Unit = {
    val indexInfoTuple = _turn_info.zipWithIndex.find { case ((entityElement, _, _, _), _) => entityElement == old_char }
    indexInfoTuple.foreach { case (_, index) =>
      _turn_info.remove(index)
      updateIndex(index)
    }
    checkEntitiesRestrictions()
  }


  private def addReady(new_tuple:(Int, Int)): Unit =  {
    if (_turn_ready.isEmpty) {
      _turn_ready += new_tuple
    }
    else{
      for (i <- _turn_ready.indices){
        if (new_tuple._2 >= _turn_ready(i)._2){
          _turn_ready.insert(i, new_tuple)
          return
        }
      }
      _turn_ready += new_tuple
    }
  }

  def nextAttacker: Entity = {
    val next_attacker: Entity = _turn_info(_turn_ready(0)._1)._1
    next_attacker
  }

  def dequeueReady(): Entity = {
    val next_attacker = nextAttacker
    val dequeueIndex = _turn_ready(0)._1
    _turn_ready.remove(0)
    _turn_wait += dequeueIndex
    next_attacker

  }

  def maxBarValue(entity:Entity): Int = {
    entity.barValue
  }

  def turn_entities: ArrayBuffer[Entity] = {
    _turn_info.map { case (entity, _, _, _) => entity }
  }


  def turn_wait: ArrayBuffer[Int] = _turn_wait
  def turn_ready: ArrayBuffer[(Int, Int)] = _turn_ready
  def turn_info: ArrayBuffer[(Entity, Int, Int, Boolean)] = _turn_info
  
}
