import entity.character.{BlackMage, WhiteMage}
import munit.FunSuite
import weapon.{Wand, Staff}

class MagicCharacterTest extends FunSuite {


  test("AbstractMagicCharacter should initialize correctly") {

    val blackMage = new BlackMage("Gandalf", 100, 20, 30, new Wand("MagicWand", 50, 10, null, 20), 150) : BlackMage
    assert(blackMage.magicPoints == 150)
    assert(blackMage.currentMagicPoints == 150)
    assert(blackMage.name == "Gandalf")
    assert(blackMage.hit_points == 100)
    assert(blackMage.defense == 20)
    assert(blackMage.weight == 30)
    assert(blackMage.equipped_weapon.isInstanceOf[Wand])
  }

  test("AbstractMagicCharacter should update currentMagicPoints correctly") {
    val whiteMage = new WhiteMage("Merlin", 120, 15, 25, null, 200)
    whiteMage.currentMagicPoints -= 50
    assert(whiteMage.currentMagicPoints == 150)
  }

  test("BlackMage should have correct default magic points") {
    val blackMage = new BlackMage("Dumbledore", 200, 25, 35, null, 300)
    assert(blackMage.magicPoints == 300)
  }

  test("WhiteMage should have correct default magic points") {
    val whiteMage = new WhiteMage("Saruman", 180, 20, 30, null, 250)
    assert(whiteMage.magicPoints == 250)
  }

  test("WhiteMage should update currentMagicPoints correctly") {
    val whiteMage = new WhiteMage("Gargamel", 220, 30, 40, null, 200)
    whiteMage.currentMagicPoints -= 50
    assert(whiteMage.currentMagicPoints == 150)
  }

}
