import munit.FunSuite
import character.{Character, Warrior}
import weapon.{Axe, Bow, Sword}
class WeaponTest extends FunSuite {

  test("AbstractWeapon should initialize correctly") {
    val axe = new Axe("Battle Axe", 60, 20, null) : Axe
    assert(axe.name == "Battle Axe")
    assert(axe.attackPoints == 60)
    assert(axe.weight == 20)
    assert(axe.owner == null)
  }

  test("AbstractWeapon should update owner correctly") {
    val character = new Warrior("Knight", 200, 30, 50, null)
    val sword = new Sword("Broadsword", 50, 15, character)
    sword.owner = character
    assert(sword.owner.name == "Knight")
  }

  test("Axe should initialize correctly") {
    val axe = new Axe("War Axe", 70, 25, null) : Axe
    assert(axe.name == "War Axe")
    assert(axe.attackPoints == 70)
    assert(axe.weight == 25)
    assert(axe.owner == null)
  }

  test("Sword should initialize correctly") {
    val sword = new Sword("Longsword", 55, 18, null) : Sword
    assert(sword.name == "Longsword")
    assert(sword.attackPoints == 55)
    assert(sword.weight == 18)
    assert(sword.owner == null)
  }

  test("Bow should initialize correctly") {
    val bow = new Bow("Composite Bow", 40, 12, null) : Bow
    assert(bow.name == "Composite Bow")
    assert(bow.attackPoints == 40)
    assert(bow.weight == 12)
    assert(bow.owner == null)
  }
}

