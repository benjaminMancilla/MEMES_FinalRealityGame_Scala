package model.turn

import exceptions.entityE.EntityOverflow
import model.entity.Entity

import scala.collection.mutable.ArrayBuffer


/**
 * Concrete class implementing a turn scheduler for managing turn-based combat mechanics in a game.
 * This class extends the TraitTurnScheduler trait.
 *
 * @param turn_entitiesI The initial entities participating in the combat.
 */
class TurnScheduler(turn_entitiesI: ArrayBuffer[Entity]) extends TraitTurnScheduler {

  /**
   * Information about the entities participating in combat, including their current action bar values and states.
   */
  val _turn_info : ArrayBuffer[(Entity, Int, Int, Boolean)] = createTurnInfo(turn_entitiesI)
  // Check restrictions on the number of entities and characters in the turn scheduler.
  checkEntitiesRestrictions()

  /**
   * The wait time for each entityE before their next turn.
   */
  private val _turn_wait: ArrayBuffer[Int] = createTurnWaitIndex(turn_entitiesI.size)

  /**
   * The ready time for each entityE, along with their index in the turn_entities array.
   */
  private val _turn_ready: ArrayBuffer[(Int, Int)] = ArrayBuffer.empty

  // Check if the number of entities and characters meets the required restrictions.
  private def checkEntitiesRestrictions(): Unit = {
    if (_turn_info.size > 8) {
      throw new EntityOverflow("Max number of entities is exceeded.")
    } else if (_turn_info.size < 2) {
      throw new EntityOverflow("Min number of entities is exceeded.")
    } else {
      var characterCounter: Int = 0
      for (tuple <- _turn_info) {
        if (tuple._1.isPlayer) {
          characterCounter+=1
        }
      }
      if (characterCounter > 3) {
        throw new EntityOverflow("Max number of characters (3) is exceeded.")
      }
    }
  }

  // Update the index values after removing an entityE.
  private def updateIndex(removedIndex: Int): Unit = {
    // Remove the index from the wait list and ready list.
    _turn_wait.filterInPlace(_ != removedIndex)
    _turn_ready.filterInPlace(tuple => tuple._1 != removedIndex)
    // Update indices in the wait list.
    for (i <- _turn_wait.indices){
      if (_turn_wait(i) > removedIndex) {
        _turn_wait(i) -= 1
      }
    }
    // Update indices in the ready list.
    for (j <- _turn_ready.indices) {
      if(_turn_ready(j)._1 > removedIndex){
        var tuple_copy = _turn_ready(j)
        val newInt = tuple_copy._1 - 1
        tuple_copy = tuple_copy.copy(_1 = newInt)
        _turn_ready.update(j, tuple_copy)
      }
    }
  }

  // Search for the index of an entityE in the ready list.
  private def searchIndexTurnReady(lookedValue: Int): Int = {
    var index: Int = 0
    while (index < _turn_ready.size) {
      if (_turn_ready(index)._1 == lookedValue) {
        return index
      }
      index += 1
    }
    -1
  }

  // Create a list of indices for the turn wait times.
  private def createTurnWaitIndex(n: Int): ArrayBuffer[Int] = {
    val arrayBuffer = ArrayBuffer[Int]()
    for (i <- 0 until n) {
      arrayBuffer += i
    }
    arrayBuffer
  }

  // Create turn information for each entityE.
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

  // Update the maximum action bar values for all entities.
  def updateMaxBars(): Unit = {
    for (i <- _turn_wait) {
      val current_entity: Entity = _turn_info(i)._1
      val max_bar: Int = maxBarValue(current_entity)
      _turn_info.update(i, (current_entity, _turn_info(i)._2, max_bar, _turn_info(i)._4))
    }
  }

  // Reset the action bar values for all entities to their initial state.
  def resetAllBarValues(): Unit = {
    for (i <- _turn_info.indices) {
      val current_entity: Entity = _turn_info(i)._1
      _turn_info.update(i, (current_entity, 0, _turn_info(i)._3, false))
    }
    // Add all entities back to the wait list and clear the ready list.
    for (tuple <- _turn_ready) {
      _turn_wait += tuple._1
    }
    _turn_ready.clear()
  }

  // Reset the action bar value for a specific entityE to its initial state.
  def resetBarValue(entity: Entity): Unit = {
    val index = _turn_info.indexWhere { case (e, _, _, _) => e == entity }
    if (index != -1) {
      val updatedTuple = _turn_info(index).copy(_2 = 0, _4 = false)
      _turn_info.update(index, updatedTuple)
      val readyIndex: Int = searchIndexTurnReady(index)
      if (readyIndex != -1) {
        _turn_ready.remove(readyIndex)
        _turn_wait += index
      }
    }
  }

  // Update the action progress of all entities by advancing their action bar values.
  def updateActionProgress(k: Int): Unit = {
    for (i <- _turn_wait) {
      val currentTuple: (Entity, Int, Int, Boolean) = _turn_info(i)
      val currentMax = currentTuple._3
      var currentBar = currentTuple._2
      var isReady = false
      currentBar += k
      if (currentBar >= currentMax) {
        isReady = true
      }
      val updatedTuple = (currentTuple._1, currentBar, currentMax, isReady)
      _turn_info.update(i, updatedTuple)
    }
  }

  // Check for entities that are in a waiting state and update their wait times.
  def checkWaitEntities(): Unit = {
    val _turn_wait_copy = _turn_wait.toList
    for (i <- _turn_wait_copy) {
      if (_turn_info(i)._4) {
        val currentMax = _turn_info(i)._3
        val currentBar = _turn_info(i)._2
        val readyTuple = (_turn_info(i)._1, currentBar - currentMax, currentMax, true)
        _turn_wait -= i
        addReady((i, currentBar - currentMax))
        _turn_info.update(i, readyTuple)
      }
    }
  }

  // Retrieve the entities that are ready to perform an action.
  def readyEntities(): ArrayBuffer[Entity] = {
    val readyEntities: ArrayBuffer[Entity] = ArrayBuffer.empty[Entity]
    for (tuple <- _turn_ready) {
      readyEntities += _turn_info(tuple._1)._1
    }
    readyEntities
  }

  // Add a new entityE to the turn scheduler.
  def addEntity(new_char: Entity): Unit =  {
    if (_turn_info.exists(tuple => tuple._1 == new_char)) {}
    else {
      _turn_info += ((new_char, 0, maxBarValue(new_char), false))
      _turn_wait += _turn_info.size-1
    }
    checkEntitiesRestrictions()
  }

  // Remove an entityE from the turn scheduler.
  def removeEntity(old_char: Entity): Unit = {
    val indexInfoTuple = _turn_info.zipWithIndex.find { case ((entityElement, _, _, _), _) => entityElement == old_char }
    indexInfoTuple.foreach { case (_, index) =>
      _turn_info.remove(index)
      updateIndex(index)
    }
    checkEntitiesRestrictions()
  }

  // Add an entityE to the ready list.
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

  // Retrieve the next entityE to perform an action.
  def nextAttacker: Entity = {
    val next_attacker: Entity = _turn_info(_turn_ready(0)._1)._1
    next_attacker
  }

  // Dequeue the next entityE that is ready to perform an action.
  def dequeueReady(): Entity = {
    val next_attacker = nextAttacker
    val dequeueIndex = _turn_ready(0)._1
    _turn_ready.remove(0)
    _turn_wait += dequeueIndex
    next_attacker

  }

  // Calculate the maximum action bar value for an entityE.
  def maxBarValue(entity:Entity): Int = {
    entity.barValue
  }

  // Get the list of entities participating in combat.
  def turn_entities: ArrayBuffer[Entity] = {
    _turn_info.map { case (entity, _, _, _) => entity }
  }

  // Get the wait list of entities.
  def turn_wait: ArrayBuffer[Int] = _turn_wait
  // Get the ready list of entities.
  def turn_ready: ArrayBuffer[(Int, Int)] = _turn_ready
  // Get the turn information for all entities.
  def turn_info: ArrayBuffer[(Entity, Int, Int, Boolean)] = _turn_info

  def updateEntity(entity: Entity): Unit = {
    val index = _turn_info.indexWhere { case (e, _, _, _) => e == entity }
    if (index != -1) {
      val currentBar = _turn_info(index)._2
      val currentMax = _turn_info(index)._3
      val isReady = _turn_info(index)._4
      _turn_info.update(index, (entity, currentBar, currentMax, isReady))
    }
  }

}
