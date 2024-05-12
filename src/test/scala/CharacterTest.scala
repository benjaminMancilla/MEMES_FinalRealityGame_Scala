import entity.character.{Ninja, Paladin, Warrior}
import weapon.{Sword, Weapon}

class CharacterTest extends munit.FunSuite {

  // No me funcionaba porque lo estaba escribiendo mal xd, luego tal vez actualizo los tests usando esto
  def BeforeEach(context: BeforeEach): Unit = {
  }
  val sword: Sword =  new Sword("Sword", 50, 10)

  test("AbstractCharacter should initialize correctly") {
    val warrior = new Warrior("Conan", 200, 30, 40) : Warrior
    warrior.changeWeapon(sword)
    assert(warrior.name == "Conan")
    assert(warrior.hit_points == 200)
    assert(warrior.defense == 30)
    assert(warrior.weight == 40)
    assert(warrior.equipped_weapon.name == "Sword")
    warrior.unequipWeapon()
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
}

