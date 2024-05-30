import entity.character.{BlackMage, MagicCharacter, Ninja, Paladin, Warrior, WhiteMage}
import exceptions.InvalidStatException
import munit.FunSuite
import weapon.{Axe, Bow, Staff, Sword, Wand}

class MagicWeaponTest extends FunSuite {

  test("Negative magic points are not allowed"){
    intercept[InvalidStatException] {
      new Staff("Magic Staff", 10, 10, -19)
    }

  }

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
    character.changeWeapon(Some(wand))
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

  test("Weapons should accept the correct Characters") {
    val staff = new Staff("Staff", 10, 10, 10)
    val wand = new Wand("Wand", 10, 10, 10)


    val warrior = new Warrior("Entity1", 100, 10, 20)
    val ninja = new Ninja("Entity1", 100, 10, 20)
    val paladin = new Paladin("Entity1", 100, 10, 20)
    val blackMage = new BlackMage("Entity1", 100, 10, 20, 10)
    val whiteMage = new WhiteMage("Entity1", 100, 10, 20, 10)

    //wand
    assert(!wand.canBeEquipped(warrior))
    assert(wand.canBeEquipped(ninja))
    assert(!wand.canBeEquipped(paladin))
    assert(wand.canBeEquipped(blackMage))
    assert(wand.canBeEquipped(whiteMage))

    //staff
    assert(!staff.canBeEquipped(warrior))
    assert(!staff.canBeEquipped(ninja))
    assert(!staff.canBeEquipped(paladin))
    assert(staff.canBeEquipped(blackMage))
    assert(staff.canBeEquipped(whiteMage))


  }
}