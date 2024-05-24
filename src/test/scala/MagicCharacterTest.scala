import entity.character.{BlackMage, WhiteMage}
import exceptions.InvalidStatException
import munit.FunSuite
import weapon.{MagicWeapon, Staff, Wand}

class MagicCharacterTest extends FunSuite {
  val wand: MagicWeapon = new Wand("MagicWand", 50, 10, 20)

  test("Magic points can not be negative"){
    intercept[InvalidStatException]{
      new BlackMage("Gandalf", 100, 20, 30, -1)
    }
  }

  test("AbstractMagicCharacter should initialize correctly") {

    val blackMage = new BlackMage("Gandalf", 100, 20, 30, 150) : BlackMage
    blackMage.changeWeapon(Some(wand))
    assert(blackMage.magicPoints == 150)
    assert(blackMage.currentMagicPoints == 150)
    assert(blackMage.name == "Gandalf")
    assert(blackMage.hit_points == 100)
    assert(blackMage.defense == 20)
    assert(blackMage.weight == 30)
    assert(blackMage.equipped_weapon.exists(_.isInstanceOf[Wand]))
    blackMage.unequipWeapon()
  }

  test("AbstractMagicCharacter should update currentMagicPoints correctly") {
    val whiteMage = new WhiteMage("Merlin", 120, 15, 25, 200)
    whiteMage.changeWeapon(Some(wand))
    whiteMage.currentMagicPoints -= 50
    assert(whiteMage.currentMagicPoints == 150)
    whiteMage.unequipWeapon()
  }

  test("BlackMage should have correct default magic points") {
    val blackMage = new BlackMage("Dumbledore", 200, 25, 35, 300)
    blackMage.changeWeapon(Some(wand))
    assert(blackMage.magicPoints == 300)
    blackMage.unequipWeapon()
  }

  test("WhiteMage should have correct default magic points") {
    val whiteMage = new WhiteMage("Saruman", 180, 20, 30, 250)
    whiteMage.changeWeapon(Some(wand))
    assert(whiteMage.magicPoints == 250)
    whiteMage.unequipWeapon()
  }

  test("WhiteMage should update currentMagicPoints correctly") {
    val whiteMage = new WhiteMage("Gargamel", 220, 30, 40, 200)
    whiteMage.changeWeapon(Some(wand))
    whiteMage.currentMagicPoints -= 50
    assert(whiteMage.currentMagicPoints == 150)
  }

}
