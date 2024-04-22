import entity.character.{Ninja, Paladin, Warrior}
import weapon.{Sword, Weapon}
class CharacterTest extends munit.FunSuite {

  // No me funcionaba porque lo estaba escribiendo mal xd, luego tal vez actualizo los tests usando esto
  def BeforeEach(context: BeforeEach): Unit = {
  }

  test("AbstractCharacter should initialize correctly") {
    val warrior = new Warrior("Conan", 200, 30, 40, new Sword("Sword", 50, 10, null)) : Warrior
    assert(warrior.name == "Conan")
    assert(warrior.hit_points == 200)
    assert(warrior.defense == 30)
    assert(warrior.weight == 40)
    assert(warrior.equipped_weapon.name == "Sword")
  }

  test("AbstractCharacter should update currentHitPoints correctly") {
    val paladin = new Paladin("Arthur", 250, 35, 50, new Sword("Sword", 50, 10, null))
    paladin.current_hit_points -= 50
    assert(paladin.current_hit_points == 200)
  }

  test("AbstractCharacter should update characterState correctly") {
    val ninja = new Ninja("Hattori", 150, 25, 30, new Sword("Sword", 50, 10, null))
    ninja.state = false
    assert(!ninja.state)
  }

  test("AbstractCharacter should change equipped weapon correctly") {
    val warrior = new Warrior("Barbarian", 180, 25, 45, new Sword("Sword", 50, 10, null))
    val newWeapon = new Sword("Mace", 50, 10, null)
    warrior.changeWeapon(newWeapon)
    assert(warrior.equipped_weapon.name == "Mace")
  }
}

