import munit.FunSuite
import weapon.{Sword, Weapon}
import character.{Ninja, Paladin, Warrior}
class CharacterTest extends FunSuite {

  test("AbstractCharacter should initialize correctly") {
    val warrior = new Warrior("Conan", 200, 30, 40, new Sword("Sword", 50, 10, null)) : Warrior
    assert(warrior.name == "Conan")
    assert(warrior.hitPoints == 200)
    assert(warrior.defense == 30)
    assert(warrior.weight == 40)
    assert(warrior.equippedWeapon.name == "Sword")
  }

  test("AbstractCharacter should update currentHitPoints correctly") {
    val paladin = new Paladin("Arthur", 250, 35, 50, new Sword("Sword", 50, 10, null))
    paladin.currentHitPoints -= 50
    assert(paladin.currentHitPoints == 200)
  }

  test("AbstractCharacter should update characterState correctly") {
    val ninja = new Ninja("Hattori", 150, 25, 30, new Sword("Sword", 50, 10, null))
    ninja.characterState = false
    assert(ninja.characterState == false)
  }

  test("AbstractCharacter should change equipped weapon correctly") {
    val warrior = new Warrior("Barbarian", 180, 25, 45, new Sword("Sword", 50, 10, null))
    val newWeapon = new Sword("Mace", 50, 10, null)
    warrior.changeWeapon(newWeapon)
    assert(warrior.equippedWeapon.name == "Mace")
  }
}

