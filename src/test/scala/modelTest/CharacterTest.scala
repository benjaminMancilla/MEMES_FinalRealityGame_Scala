package modelTest

import exceptions.entityE.{HealingDeadEntity, ProhibitedTarget}
import exceptions.weaponE.{EmptyWeaponException, InvalidWeaponException}
import exceptions.{InvalidNameException, InvalidStatException}
import model.entity.character.commonCharacter.{Ninja, Paladin, Warrior}
import model.entity.character.magicCharacter.{BlackMage, WhiteMage}
import model.entity.enemy.ConcreteEnemy
import model.weapon.commonWeapon.{Axe, Bow, Sword}
import model.weapon.magicWeapon.{Staff, Wand}

import scala.collection.mutable.ArrayBuffer

class CharacterTest extends munit.FunSuite {
  val sword: Sword =  new Sword("Sword", 50, 10)

  test("AbstractCharacter should initialize correctly") {
    val warrior = new Warrior("Conan", 200, 30, 40) : Warrior
    warrior.changeWeapon(Some(sword))
    assert(warrior.name == "Conan")
    assert(warrior.hit_points == 200)
    assert(warrior.defense == 30)
    assert(warrior.weight == 40)
    assert(warrior.equipped_weapon.exists(_.name == "Sword"))
    assert(warrior.typeName == "Warrior")
    warrior.unequipWeapon()
  }

  test("Entity should not initialize with invalid parameters") {
    intercept[InvalidNameException] {
      new Warrior("", 0, 30, 40)
    }
    intercept[InvalidStatException] {
      new Warrior("Conan", 0, 30, 40)
    }
    intercept[InvalidStatException] {
      new Warrior("Conan", 200, -1, 40)
    }
    intercept[InvalidStatException] {
      new Warrior("Conan", 200, 30, -1)
    }


  }

  test("AbstractCharacter should update currentHitPoints correctly") {
    val paladin = new Paladin("Arthur", 250, 35, 50)
    paladin.changeWeapon(Some(sword))
    paladin.current_hit_points -= 50
    assert(paladin.current_hit_points == 200)
    paladin.unequipWeapon()
  }

  test("AbstractCharacter should update characterState correctly") {
    val ninja = new Ninja("Hattori", 150, 25, 30)
    ninja.changeWeapon(Some(sword))
    ninja.state = false
    assert(!ninja.state)
    ninja.unequipWeapon()
  }

  test("AbstractCharacter should change equipped weapon correctly") {
    val warrior = new Warrior("Barbarian", 180, 25, 45)
    warrior.changeWeapon(Some(sword))
    val newWeapon = new Sword("Mace", 50, 10)
    warrior.changeWeapon(Some(newWeapon))
    assert(warrior.equipped_weapon.exists(_.name == "Mace"))
  }

  private val entity1 = new Warrior("Entity1", 100, 10, 20)
  entity1.changeWeapon(Some(new Sword("Mace", 50, 10)))
  private val entity2 = new ConcreteEnemy("Entity2", 80, 10, 30, 10)

  test("doGenericAttack should reduce the hit points of the attacked entity") {
    val initialHitPoints = entity2.current_hit_points
    entity1.doAttack(entity2)
    assert(entity2.current_hit_points < initialHitPoints)
    assert(entity2.current_hit_points == 40)
  }

  test("doGenericAttack should not reduce hit points if the attacker is out of combat") {
    entity1.state = false
    val initialHitPoints = entity2.current_hit_points
    entity1.doAttack(entity2)
    assert(entity2.current_hit_points == initialHitPoints)
  }

  test("receiveDamage should reduce the current hit points of the entity") {
    val initialHitPoints = entity1.current_hit_points
    entity1.receiveDamage(20)
    assert(entity1.current_hit_points < initialHitPoints)
  }

  test("receiveDamage should set the state to false if hit points reach 0") {
    entity1.receiveDamage(200)
    assert(!entity1.state)
  }

  test("doDamage should detect how much extra damage is dealt when killing an entity") {
    val entity3 = new Warrior("Entity1", 500, 10, 20)
    entity3.changeWeapon(Some(new Sword("Mace", 500, 10)))
    val entity4 = new ConcreteEnemy("Entity2", 80, 10, 30, 10)
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      entity3.doAttack(entity4)
    }
    val output = stream.toString()
    assert(output.contains("Entity1 attacks Entity2"))
    assert(output.contains("Entity1 has defeated Entity2 with a 410 of extra DMG!!!!!"))
    assert(entity4.current_hit_points == 0)

    val entity5 = new Warrior("Entity1", 10, 10, 20)
    entity5.changeWeapon(Some(new Sword("Mace", 90, 10)))
    val entity6 = new ConcreteEnemy("Entity2", 80, 10, 30, 10)
    val stream2 = new java.io.ByteArrayOutputStream()
    Console.withOut(stream2) {
      entity5.doAttack(entity6)
    }
    val output2 = stream2.toString()
    assert(output2.contains("Entity1 attacks Entity2"))
    assert(output2.contains("Entity1 has defeated Entity2 precisely!!"))
    assert(entity6.current_hit_points == 0)
  }

  test("Character can not use a weapon with owner") {
    val entity = new Warrior("Entity1", 100, 10, 20)
    val sword =  new Sword("Sword", 50, 10)
    entity.changeWeapon(Some(sword))
    val entity2 = new Warrior("Entity2", 100, 10, 20)
    intercept[InvalidWeaponException]{
      entity2.changeWeapon(Some(sword))
    }
  }

  test("Character can not use a weapon that does not fit their class") {
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val ninja = new Ninja("Entity1", 100, 10, 20)
    val paladin = new Paladin("Entity1", 100, 10, 20)
    val blackMage = new BlackMage("Entity1", 100, 10, 20, 10)
    val whiteMage = new WhiteMage("Entity1", 100, 10, 20, 10)
    val sword =  new Sword("Sword", 50, 10)
    val bow =  new Bow("Bow", 50, 10)
    val axe =  new Axe("Axe", 50, 10)
    val staff =  new Staff("Staff", 50, 10, 10)
    val wand =  new Wand("Wand", 50, 10, 10)
    val characters = ArrayBuffer(warrior, ninja, paladin, blackMage, whiteMage)
    val weapons = ArrayBuffer(wand, staff, bow, axe, sword)
    for (i <- 0 to 4) {

      intercept[InvalidWeaponException] {
        characters(i).changeWeapon(Some(weapons(i)))
      }
    }

  }

  test("Character should not be able to attack if it is disarmed"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val enemy = new ConcreteEnemy("Entity1", 100, 10, 20, 10)
    intercept[EmptyWeaponException]{
      warrior.doAttack(enemy)
    }


  }

  test("Character can not attack another Character"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val ninja = new Ninja("Entity1", 100, 10, 20)
    intercept[ProhibitedTarget]{
      warrior.doAttack(ninja)
    }

  }

  test("Character should be able to unequip the weapon"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val sword =  new Sword("Sword", 50, 10)
    warrior.changeWeapon(Some(sword))
    assert(warrior.equipped_weapon.exists(_.name == "Sword"))
    assert(sword.owner.exists(_.name == "Entity1"))
    warrior.unequipWeapon()
    assert(warrior.equipped_weapon.isEmpty)
    assert(sword.owner.isEmpty)
    warrior.changeWeapon(Some(sword))
    warrior.changeWeapon(None)
    assert(warrior.equipped_weapon.isEmpty)
    assert(sword.owner.isEmpty)

  }

  test("A character should be able to heal correctly"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    warrior.receiveDamage(90)
    warrior.receiveHealing(20)
    assert(warrior.current_hit_points == 40)

  }

  test("A character should not be able to overheal"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    warrior.receiveDamage(20)
    warrior.receiveHealing(2000)
    assert(warrior.current_hit_points == warrior.hit_points)

  }

  test("If a dead character receives healing, should revive"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    warrior.receiveDamage(1000)
    assert(!warrior.state)
    warrior.receiveHealing(10)
    assert(warrior.current_hit_points == 10)
    assert(warrior.state)

  }

  test("A character should be able to heal an entityE"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val warrior2 = new Warrior("Entity2", 100, 10, 20)
    warrior2.receiveDamage(40)
    warrior.receiveDamage(40)
    warrior.doHeal(warrior2, 10)
    warrior2.doHealing(warrior, 10)
    assert(warrior2.current_hit_points == 80)
    assert(warrior.current_hit_points == 80)

  }

  test("A character should not be able to heal a dead entityE") {
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val warrior2 = new Warrior("Entity2", 100, 10, 20)
    warrior2.receiveDamage(1000)
    intercept[HealingDeadEntity] {
      warrior.doHeal(warrior2, 10)
    }
    intercept[HealingDeadEntity] {
      warrior.doHealing(warrior2, 10)
    }
  }

  test("A dead character should not be able to heal"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val warrior2 = new Warrior("Entity2", 100, 10, 20)
    warrior2.receiveDamage(1000)
    intercept[HealingDeadEntity] {
      warrior2.doHeal(warrior, 10)
    }
    intercept[HealingDeadEntity] {
      warrior2.doHealing(warrior, 10)
    }


  }








}

