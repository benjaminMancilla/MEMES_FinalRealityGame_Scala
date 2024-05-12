import entity.character.{MagicCharacter, WhiteMage}
import munit.FunSuite
import weapon.{Staff, Wand}

class MagicWeaponTest extends FunSuite {

  test("AbstractMagicWeapon should initialize correctly") {
    val staff = new Staff("Magic Staff", 70, 15, 50) : Staff
    assert(staff.name == "Magic Staff")
    assert(staff.attackPoints == 70)
    assert(staff.weight == 15)
    assert(staff.owner.isEmpty)
    assert(staff.magicAttack == 50)
  }

  test("AbstractMagicWeapon should update owner correctly") {
    val character = new WhiteMage("Wizard", 180, 25, 40, 100)
    val wand = new Wand("Magic Wand", 60, 12, 40)
    character.changeWeapon(wand)
    assert(wand.owner.exists(_.name == "Wizard"))
  }

  test("Staff should initialize correctly") {
    val staff = new Staff("Enchanted Staff", 65, 14, 45) : Staff
    assert(staff.name == "Enchanted Staff")
    assert(staff.attackPoints == 65)
    assert(staff.weight == 14)
    assert(staff.owner.isEmpty)
    assert(staff.magicAttack == 45)
  }

  test("Wand should initialize correctly") {
    val wand = new Wand("Wizard Wand", 55, 10, 35) : Wand
    assert(wand.name == "Wizard Wand")
    assert(wand.attackPoints == 55)
    assert(wand.weight == 10)
    assert(wand.owner.isEmpty)
    assert(wand.magicAttack == 35)
  }
}