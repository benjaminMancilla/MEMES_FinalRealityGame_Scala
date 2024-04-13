import character.MagicCharacter
import munit.FunSuite
import character.WhiteMage
import weapon.{Staff, Wand}

class MagicWeaponTest extends FunSuite {

  test("AbstractMagicWeapon should initialize correctly") {
    val staff = new Staff("Magic Staff", 70, 15, null, 50) : Staff
    assert(staff.name == "Magic Staff")
    assert(staff.attackPoints == 70)
    assert(staff.weight == 15)
    assert(staff.owner == null)
    assert(staff.magicAttack == 50)
  }

  test("AbstractMagicWeapon should update owner correctly") {
    val character = new WhiteMage("Wizard", 180, 25, 40, null, 100)
    val wand = new Wand("Magic Wand", 60, 12, null, 40)
    wand.owner = character
    assert(wand.owner.name == "Wizard")
  }

  test("Staff should initialize correctly") {
    val staff = new Staff("Enchanted Staff", 65, 14, null, 45) : Staff
    assert(staff.name == "Enchanted Staff")
    assert(staff.attackPoints == 65)
    assert(staff.weight == 14)
    assert(staff.owner == null)
    assert(staff.magicAttack == 45)
  }

  test("Wand should initialize correctly") {
    val wand = new Wand("Wizard Wand", 55, 10, null, 35) : Wand
    assert(wand.name == "Wizard Wand")
    assert(wand.attackPoints == 55)
    assert(wand.weight == 10)
    assert(wand.owner == null)
    assert(wand.magicAttack == 35)
  }
}