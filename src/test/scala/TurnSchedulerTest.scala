import entity.Entity
import entity.character.Character
import entity.character.{Ninja, Paladin, Warrior}
import entity.enemy.ConcreteEnemy
import munit.FunSuite
import turn.TurnScheduler
import weapon.{Sword, EmptyWeapon}


import scala.collection.mutable.ArrayBuffer

class TurnSchedulerTest extends FunSuite {

  test("TurnScheduler should initialize correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40, new Sword("Sword", 50, 10, null))
    val entity2 = new Ninja("Hattori", 150, 25, 30, new Sword("Sword", 50, 10, null))
    val entity3 = new Paladin("Dohvakin", 150, 25, 30, new EmptyWeapon(_owner = null))

    val array: ArrayBuffer[Entity]= ArrayBuffer()
    val array2: ArrayBuffer[Entity]= ArrayBuffer(entity1, entity2, entity3)
    val turnScheduler = new TurnScheduler(array)
    val turnScheduler2 = new TurnScheduler(array2)
    assert(turnScheduler.turn_entities.isEmpty)
    assert(turnScheduler.turn_wait.isEmpty)

    assert(turnScheduler2.turn_entities.nonEmpty)
    assert(turnScheduler2.turn_wait.nonEmpty)


    turnScheduler.addEntity(entity1)
    turnScheduler.addEntity(entity2)
    turnScheduler.addEntity(entity3)

    assert(turnScheduler.turn_entities.size == 3)
    assert(turnScheduler.turn_wait.size == 3)

    turnScheduler.removeEntity(entity1)
    assert(turnScheduler.turn_entities.size == 2)
    assert(turnScheduler.turn_wait.size == 2)
    assert(!turnScheduler.turn_entities.contains(entity1))

  }

  test("TurnScheduler should update entity bars correctly") {
    val entity1 = new Paladin("Arthur", 250, 35, 50, new Sword("Sword", 50, 10, null))
    val entity2 = new ConcreteEnemy("Goblin", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler()
    turnScheduler.addEntity(entity1)
    turnScheduler.addEntity(entity2)

    turnScheduler.updateActionProgress(5)
    turnScheduler.checkWaitEntities()

    assert(turnScheduler.turn_wait.head._2 == 5)
  }

  test("TurnScheduler should reset entity bars correctly") {
    val entity1 = new ConcreteEnemy("Goblin2", 30, 5, 40, 15)
    val entity2 = new ConcreteEnemy("Goblin3", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler()
    turnScheduler.addEntity(entity1)
    turnScheduler.addEntity(entity2)

    turnScheduler.updateActionProgress(12)
    turnScheduler.resetAllBarValues()

    assert(turnScheduler.turn_wait.forall(_._2 == 0))

  }

  test("TurnScheduler should update maxbars correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40, new Sword("Sword", 50, 10, null))
    val entity2 = new Ninja("Hattori", 150, 25, 30, new Sword("Sword", 50, 10, null))

    val array: ArrayBuffer[Entity]= ArrayBuffer(entity1, entity2)
    val turnScheduler = new TurnScheduler(array)
    assert(turnScheduler.turn_wait(0)._3 == 40 + 10/2)
    assert(turnScheduler.turn_wait(1)._3 == 30 + 10/2)

    turnScheduler._turn_wait(0)._1.asInstanceOf[Character].changeWeapon(new Sword("Sword", 50, 20, null))
    turnScheduler._turn_entities(0).asInstanceOf[Character].changeWeapon(new Sword("Sword", 50, 20, null))

    turnScheduler.updateMaxBars()
    println(turnScheduler.turn_wait(0)._3)
    assert(turnScheduler.turn_wait(0)._3 == 40 + 20/2)
    assert(turnScheduler.turn_wait(1)._3 == 30 + 10/2)

  }

  test("TurnScheduler should reset entity 1 bar correctly") {
    val entity1 = new ConcreteEnemy("Goblin2", 30, 5, 40, 15)
    val entity2 = new ConcreteEnemy("Goblin3", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler()
    turnScheduler.addEntity(entity1)
    turnScheduler.addEntity(entity2)

    turnScheduler.updateActionProgress(12)
    turnScheduler.resetBarValue(entity1)
    assert(turnScheduler.turn_wait(0)._2 == 0)

  }

  test("TurnScheduler should check entity bars correctly") {
    val entity1 = new ConcreteEnemy("Goblin1", 30, 5, 40, 15)
    val entity2 = new ConcreteEnemy("Goblin2", 30, 5, 20, 15)

    val turnScheduler = new TurnScheduler()
    turnScheduler.addEntity(entity1)
    turnScheduler.addEntity(entity2)

    turnScheduler.updateActionProgress(32)
    assert(turnScheduler.turn_wait.forall(_._2 == 32))
    assert(turnScheduler.turn_wait(1)._4)
    turnScheduler.checkWaitEntities()
    assert(!turnScheduler.turn_wait.contains((entity2, 32, 20, false)))
    assert(!turnScheduler.turn_wait.contains((entity2, 32, 20, true)))
    assert(turnScheduler.turn_wait.size == 1)


  }

  test("TestScheduler should return entities in order for play") {
    val entity1 = new ConcreteEnemy("Goblin1", 30, 5, 40, 15) // 40
    val entity2 = new ConcreteEnemy("Goblin2", 30, 5, 20, 15) // 20
    val entity3 = new Warrior("Conan", 200, 30, 40, new Sword("Sword", 50, 10, null)) // 40 + 5
    val entity4 = new Ninja("Hattori", 150, 25, 30, new Sword("Sword", 50, 10, null)) // 30 + 5

    val turnScheduler = new TurnScheduler()
    turnScheduler.addEntity(entity1)
    turnScheduler.addEntity(entity2)
    turnScheduler.addEntity(entity3)
    turnScheduler.addEntity(entity4)
    assert(turnScheduler.turn_wait.size == 4)
    turnScheduler.updateActionProgress(50)
    turnScheduler.checkWaitEntities()
    assert(turnScheduler.turn_wait.isEmpty)
    assert(turnScheduler.turn_ready.nonEmpty)
    val test1_tuple = turnScheduler.nextAttacker
    assert(test1_tuple._1 == entity2)
    assert(test1_tuple._2 == 30)
    assert(turnScheduler.turn_ready.size == 3)
    val test2_tuple = turnScheduler.nextAttacker
    assert(test2_tuple._1 == entity4)
    assert(test2_tuple._2 == 15)
    assert(turnScheduler.turn_ready.size == 2)
    val test3_tuple = turnScheduler.nextAttacker
    assert(test3_tuple._1 == entity1)
    assert(test3_tuple._2 == 10)
    assert(turnScheduler.turn_ready.size == 1)
    val test4_tuple = turnScheduler.nextAttacker
    assert(test4_tuple._1 == entity3)
    assert(test4_tuple._2 == 5)
    assert(turnScheduler.turn_ready.isEmpty)





  }


}
