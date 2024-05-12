import entity.Entity
import entity.character.{Ninja, Paladin, Warrior}
import entity.enemy.ConcreteEnemy
import munit.FunSuite
import turn.TurnScheduler
import weapon.Sword

import scala.collection.mutable.ArrayBuffer

class TurnSchedulerTest extends FunSuite {

  test("TurnScheduler should initialize correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40)
    val entity2 = new Ninja("Hattori", 150, 25, 30)
    val entity3 = new Paladin("Dohvakin", 150, 25, 30)
    val enemy1 = new ConcreteEnemy("Goblin1", 30, 5, 40, 15)

    val array: ArrayBuffer[Entity] = ArrayBuffer(entity1, entity2, entity3, enemy1)
    val turnScheduler = new TurnScheduler(array)
    assert(turnScheduler.turn_entities.size == 4)
    assert(turnScheduler.turn_wait.size == 4)

  }

  test("TurnScheduler should not initialize with less than 3 characters, 4 entities or with more than 8 entities") {
    val entity1 = new Warrior("Conan", 200, 30, 40)
    val entity2 = new Ninja("Hattori", 150, 25, 30)
    val entity3 = new ConcreteEnemy("Juan", 100, 20, 30, 10)

    intercept[IllegalArgumentException] {
      new TurnScheduler(ArrayBuffer(entity1, entity2))
    }

    intercept[IllegalArgumentException] {
      new TurnScheduler(ArrayBuffer(entity1, entity2, entity1))
    }

    intercept[IllegalArgumentException] {
      new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, entity3))
    }

    intercept[IllegalArgumentException] {
      new TurnScheduler(ArrayBuffer(entity1, entity2, entity2, entity2))
    }

    intercept[IllegalArgumentException] {
      new TurnScheduler(ArrayBuffer(entity1, entity2, entity2, entity3, entity3, entity3, entity3, entity3, entity3))
    }

    intercept[IllegalArgumentException] {
      new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, entity3, entity3, entity3, entity3))
    }

    intercept[IllegalArgumentException] {
      new TurnScheduler(ArrayBuffer.empty[Entity])
    }
  }

  test("TurnScheduler should update entity bars correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40)
    val sword1 = new Sword("Sword", 50, 10)
    entity1.changeWeapon(sword1)
    val entity2 = new Ninja("Hattori", 150, 25, 30)
    val entity3 = new Paladin("Dohvakin", 150, 25, 30)
    val enemy1 = new ConcreteEnemy("Goblin1", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, enemy1))
    turnScheduler.addEntity(enemy1)
    turnScheduler.addEntity(enemy1)
    assert(turnScheduler.turn_wait.size == 4)

    turnScheduler.updateActionProgress(5)
    turnScheduler.checkWaitEntities()

    assert(turnScheduler.turn_ready.isEmpty)
    assert(turnScheduler.turn_wait.size == 4)
  }

  test("TurnScheduler should reset entity bars correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40)
    val sword1 = new Sword("Sword", 50, 10)
    entity1.changeWeapon(sword1)
    val entity2 = new Ninja("Hattori", 150, 25, 30)
    val entity3 = new Paladin("Dohvakin", 150, 25, 30)
    val enemy1 = new ConcreteEnemy("Goblin2", 30, 5, 40, 15)
    val enemy2 = new ConcreteEnemy("Goblin3", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, enemy1))
    turnScheduler.addEntity(enemy2)

    turnScheduler.updateActionProgress(12)
    turnScheduler.resetAllBarValues()

    assert(turnScheduler.turn_wait.size == 5)
  }

  test("TurnScheduler should update maxbars correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40)
    entity1.changeWeapon(new Sword("Sword", 50, 10))
    val entity2 = new Ninja("Hattori", 150, 25, 30)
    entity2.changeWeapon(new Sword("Sword", 50, 10))
    val entity3 = new Paladin("Dohvakin", 150, 25, 30)
    val enemy1 = new ConcreteEnemy("Goblin2", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, enemy1))
    assert(turnScheduler.turn_info(0)._3 == 40 + 10/2)
    assert(turnScheduler.turn_info(1)._3 == 30 + 10/2)

    entity1.changeWeapon(new Sword("Sword", 50, 20))
    turnScheduler.updateMaxBars()

    assert(turnScheduler.turn_info(0)._3 == 40 + 20/2)
    assert(turnScheduler.turn_info(1)._3 == 30 + 10/2)
  }

  test("TurnScheduler should reset entity 1 bar correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40)
    entity1.changeWeapon(new Sword("Sword", 50, 10))
    val entity2 = new Ninja("Hattori", 150, 25, 30)
    entity2.changeWeapon(new Sword("Sword", 50, 10))
    val entity3 = new Paladin("Dohvakin", 150, 25, 30)
    val enemy1 = new ConcreteEnemy("Goblin2", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, enemy1))

    turnScheduler.updateActionProgress(1)
    turnScheduler.checkWaitEntities()
    turnScheduler.resetBarValue(entity1)
    assert(turnScheduler.turn_info(0)._2 == 0)
    turnScheduler.updateActionProgress(100)
    turnScheduler.checkWaitEntities()
    turnScheduler.resetBarValue(entity1)
    assert(turnScheduler.turn_info(0)._2 == 0)
    assert(turnScheduler.turn_wait.size == 1)

  }

  test("TurnScheduler should check entity bars correctly") {
    val entity1 = new Warrior("Conan", 200, 30, 40)
    entity1.changeWeapon(new Sword("Sword", 50, 10))
    val entity2 = new Ninja("Hattori", 150, 25, 30)
    entity2.changeWeapon(new Sword("Sword", 50, 10))
    val entity3 = new Paladin("Dohvakin", 150, 25, 30)
    val enemy1 = new ConcreteEnemy("Goblin2", 30, 5, 40, 15)

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, enemy1))

    turnScheduler.updateActionProgress(32)
    assert(turnScheduler.turn_info.forall(_._2 == 32))
    assert(turnScheduler.turn_info(2)._4)
    turnScheduler.updateActionProgress(32)
    assert(turnScheduler.turn_wait.nonEmpty)
    turnScheduler.checkWaitEntities()
    assert(turnScheduler.turn_wait.isEmpty)

  }

  test("TestScheduler should return entities in order for play") {
    val entity1 = new ConcreteEnemy("Goblin1", 30, 5, 40, 15) // 40
    val entity2 = new ConcreteEnemy("Goblin2", 30, 5, 20, 15) // 20
    val entity3 = new Warrior("Conan", 200, 30, 40) // 40 + 5
    entity3.changeWeapon(new Sword("Sword", 50, 10))
    val entity4 = new Ninja("Hattori", 150, 25, 30) // 30 + 5
    entity4.changeWeapon(new Sword("Sword", 50, 10))
    val entity5 = new Paladin("Dohvakin", 150, 25, 30) // 30

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, entity4, entity5))
    assert(turnScheduler.turn_wait.size == 5)
    turnScheduler.updateActionProgress(50)
    turnScheduler.checkWaitEntities()
    assert(turnScheduler.turn_wait.isEmpty)
    assert(turnScheduler.turn_ready.nonEmpty)
    val test1_entity = turnScheduler.dequeueReady()
    assert(test1_entity == entity2) // 2
    assert(turnScheduler.turn_ready.size == 4)
    val test2_entity = turnScheduler.dequeueReady()
    assert(test2_entity == entity5) // 5
    assert(turnScheduler.turn_ready.size == 3)
    val test3_entity = turnScheduler.dequeueReady()
    assert(test3_entity == entity4) //4
    assert(turnScheduler.turn_ready.size == 2)
    val test4_entity = turnScheduler.dequeueReady()
    assert(test4_entity == entity1) //1
    assert(turnScheduler.turn_ready.size == 1)
    var test5_entity = turnScheduler.nextAttacker
    assert(test5_entity == entity3) //3
    assert(turnScheduler.turn_ready.size == 1)
    test5_entity = turnScheduler.dequeueReady()
    assert(test5_entity == entity3)
    assert(turnScheduler.turn_ready.isEmpty)

  }

  test("TestScheduler should be able to remove entities") {
    val entity1 = new ConcreteEnemy("Goblin1", 30, 5, 40, 15) // 40
    val entity2 = new ConcreteEnemy("Goblin2", 30, 5, 20, 15) // 20
    val entityaux = new ConcreteEnemy("Goblin3", 30, 5, 20, 15)
    val entity3 = new Warrior("Conan", 200, 30, 40) // 40 + 5
    entity3.changeWeapon(new Sword("Sword", 50, 10))
    val entity4 = new Ninja("Hattori", 150, 25, 30) // 30 + 5
    entity4.changeWeapon(new Sword("Sword", 50, 10))
    val entity5 = new Paladin("Dohvakin", 150, 25, 30) // 30

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, entity4, entity5, entityaux))
    turnScheduler.removeEntity(entity1)
    assert(turnScheduler.turn_info.size == 5)

    assert(turnScheduler.turn_wait.max == 4)
    assert(turnScheduler.turn_wait.min == 0)
    assert(turnScheduler.turn_info(turnScheduler.turn_wait(0))._1 == entity2)
    turnScheduler.updateActionProgress(1000)
    turnScheduler.checkWaitEntities()
    turnScheduler.removeEntity(entity2)
    assert(turnScheduler.turn_ready.size == 4)
    assert(turnScheduler.turn_ready(0)._1 == 3)







  }
  test("TestScheduler should be able to return all ready entities") {
    val entity1 = new ConcreteEnemy("Goblin1", 30, 5, 40, 15) // 40
    val entity2 = new ConcreteEnemy("Goblin2", 30, 5, 20, 15) // 20
    val entity3 = new Warrior("Conan", 200, 30, 40) // 40 + 5
    entity3.changeWeapon(new Sword("Sword", 50, 10))
    val entity4 = new Ninja("Hattori", 150, 25, 30) // 30 + 5
    entity4.changeWeapon(new Sword("Sword", 50, 10))
    val entity5 = new Paladin("Dohvakin", 150, 25, 30) // 30

    val turnScheduler = new TurnScheduler(ArrayBuffer(entity1, entity2, entity3, entity4, entity5))
    assert(turnScheduler.readyEntities().isEmpty)
    turnScheduler.updateActionProgress(1000)
    turnScheduler.checkWaitEntities()
    assert(turnScheduler.readyEntities().size == 5)

  }

  test(""){

  }

}

