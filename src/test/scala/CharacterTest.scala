import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}
import entity.enemy.ConcreteEnemy
import weapon.{Axe, Bow, Staff, Sword, Wand}

import scala.collection.mutable.ArrayBuffer

class CharacterTest extends munit.FunSuite {
  val sword: Sword =  new Sword("Sword", 50, 10)

  test("AbstractCharacter should initialize correctly") {
    val warrior = new Warrior("Conan", 200, 30, 40) : Warrior
    warrior.changeWeapon(sword)
    assert(warrior.name == "Conan")
    assert(warrior.hit_points == 200)
    assert(warrior.defense == 30)
    assert(warrior.weight == 40)
    assert(warrior.equipped_weapon.name == "Sword")
    assert(warrior.typeName == "Warrior")
    warrior.unequipWeapon()
  }

  test("Entity should not initialize with invalid parameters") {
    val conan = new Warrior("", 0, 30, 40)
    assert(conan.name == "Entity")
    val conan1 = new Warrior("Conan", 0, 30, 40)
    assert(conan1.hit_points == 10)
    val conan2 = new Warrior("Conan", 200, -1, 40)
    assert(conan2.defense == 0)
    val conan3 = new Warrior("Conan", 200, 30, -1)
    assert(conan3.weight == 0)

  }

  test("AbstractCharacter should update currentHitPoints correctly") {
    val paladin = new Paladin("Arthur", 250, 35, 50)
    paladin.changeWeapon(sword)
    paladin.current_hit_points -= 50
    assert(paladin.current_hit_points == 200)
    paladin.unequipWeapon()
  }

  test("AbstractCharacter should update characterState correctly") {
    val ninja = new Ninja("Hattori", 150, 25, 30)
    ninja.changeWeapon(sword)
    ninja.state = false
    assert(!ninja.state)
    ninja.unequipWeapon()
  }

  test("AbstractCharacter should change equipped weapon correctly") {
    val warrior = new Warrior("Barbarian", 180, 25, 45)
    warrior.changeWeapon(sword)
    val newWeapon = new Sword("Mace", 50, 10)
    warrior.changeWeapon(newWeapon)
    assert(warrior.equipped_weapon.name == "Mace")
  }

  private val entity1 = new Warrior("Entity1", 100, 10, 20)
  entity1.changeWeapon(new Sword("Mace", 50, 10))
  private val entity2 = new ConcreteEnemy("Entity2", 80, 10, 30, 10)

  test("doAttack should reduce the hit points of the attacked entity") {
    val initialHitPoints = entity2.current_hit_points
    entity1.doAttack(entity2, 30)
    assert(entity2.current_hit_points < initialHitPoints)
    assert(entity2.current_hit_points == 60)
  }

  test("doAttack should not reduce hit points if the attacker is out of combat") {
    entity1.state = false
    val initialHitPoints = entity2.current_hit_points
    entity1.doAttack(entity2, 30)
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
    val entity3 = new Warrior("Entity1", 100, 10, 20)
    entity3.changeWeapon(new Sword("Mace", 50, 10))
    val entity4 = new ConcreteEnemy("Entity2", 80, 10, 30, 10)
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      entity3.doAttack(entity4, 500)
    }
    val output = stream.toString()
    assert(output.contains("Entity1 attacks Entity2"))
    assert(output.contains("Entity1 has defeated Entity2 with a 410 of extra DMG!!!!!"))
    assert(entity4.current_hit_points == 0)

    val entity5 = new Warrior("Entity1", 100, 10, 20)
    entity5.changeWeapon(new Sword("Mace", 50, 10))
    val entity6 = new ConcreteEnemy("Entity2", 80, 10, 30, 10)
    val stream2 = new java.io.ByteArrayOutputStream()
    Console.withOut(stream2) {
      entity5.doAttack(entity6, 90)
    }
    val output2 = stream2.toString()
    assert(output2.contains("Entity1 attacks Entity2"))
    assert(output2.contains("Entity1 has defeated Entity2 precisely!!"))
    assert(entity6.current_hit_points == 0)
  }

  test("Character can not use a weapon with owner") {
    val entity = new Warrior("Entity1", 100, 10, 20)
    val sword =  new Sword("Sword", 50, 10)
    entity.changeWeapon(sword)
    val entity2 = new Warrior("Entity2", 100, 10, 20)
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      entity2.changeWeapon(sword)
    }
    val output = stream.toString()
    assert(output.contains("Sword is already being used by Entity1"))
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
      val stream = new java.io.ByteArrayOutputStream()
      Console.withOut(stream) {
        characters(i).changeWeapon(weapons(i))
      }
      val output = stream.toString()
      assert(output.contains(s"Cannot equip ${weapons(i).name} on this character"))


    }

  }

  test("Character should not be able to attack if it is disarmed"){
    val warrior = new Warrior("Entity1", 100, 10, 20)
    val ninja = new Ninja("Entity1", 100, 10, 20)
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      warrior.doAttack(ninja, 10)
    }
    val output = stream.toString()
    assert(output.contains("does not have an equipped weapon and cannot attack."))

    assert(ninja.current_hit_points == ninja.hit_points)


  }


}

