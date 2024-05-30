import entity.character.commonCharacter.Warrior
import entity.character.magicCharacter.{BlackMage, WhiteMage}
import entity.enemy.ConcreteEnemy
import exceptions.magic.{NoMagicPoints, NonMagicWeaponException}
import exceptions.weapon.EmptyWeaponException
import exceptions.InvalidStatException
import exceptions.entity.ProhibitedTarget
import magic.blackMagic.Fire
import magic.whiteMagic.{Heal, Paralyze, Poison, Thunder}
import munit.FunSuite
import weapon.commonWeapon.{Bow, Sword}
import weapon.magicWeapon.{MagicWeapon, Staff, Wand}

class MagicCharacterTest extends FunSuite {
  val wand: MagicWeapon = new Wand("MagicWand", 50, 10, 20)
  val wand2: MagicWeapon = new Wand("MagicWand2", 50, 10, 20)

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
    whiteMage.unequipWeapon()
  }

  test("If a cast is valid, a mage should be able to cast the spell, reducing him/her magic points") {
    val bMage = new BlackMage("Gargamel", 220, 30, 40, 200)
    val wMage = new WhiteMage("Saruman", 220, 30, 40, 200)
    bMage.changeWeapon(Some(wand))
    wMage.changeWeapon(Some(wand2))
    val enemy = new ConcreteEnemy("Entity1", 100, 10, 20, 10)
    val thunder = new Thunder
    val poison = new Poison
    bMage.castSpell(enemy, thunder)
    wMage.castSpell(enemy, poison)
    assert(bMage.currentMagicPoints == bMage.magicPoints-thunder.manaCost)
    assert(wMage.currentMagicPoints == wMage.magicPoints-poison.manaCost)
    bMage.unequipWeapon()
    wMage.unequipWeapon()

  }

  test("Should not be able to cast if the sorcerer has not enough mana") {
    val bMage = new BlackMage("Gargamel", 220, 30, 40, 200)
    val wMage = new WhiteMage("Saruman", 220, 30, 40, 200)
    bMage.changeWeapon(Some(wand))
    wMage.changeWeapon(Some(wand2))
    bMage.currentMagicPoints = 0
    wMage.currentMagicPoints = 5
    val enemy = new ConcreteEnemy("Entity1", 100, 10, 20, 10)
    val thunder = new Thunder
    val poison = new Poison
    intercept[NoMagicPoints] {
      bMage.castSpell(enemy, thunder)
    }
    intercept[NoMagicPoints] {
      wMage.castSpell(enemy, poison)
    }
    bMage.unequipWeapon()
    wMage.unequipWeapon()

  }

  test("Should not be able to cast if the sorcerer has not a magic weapon") {
    val bMage = new BlackMage("Gargamel", 220, 30, 40, 200)
    val wMage = new WhiteMage("Saruman", 220, 30, 40, 200)
    wMage.changeWeapon(Some(new Bow("Bow", 50, 10)))
    val enemy = new ConcreteEnemy("Entity1", 100, 10, 20, 10)
    val thunder = new Thunder
    val poison = new Poison
    intercept[EmptyWeaponException]{
      bMage.castSpell(enemy, thunder)
    }
    intercept[NonMagicWeaponException]{
      wMage.castSpell(enemy, poison)
    }
    bMage.unequipWeapon()
    wMage.unequipWeapon()

  }

  test("Should not be able to cast on a dead target"){
    val bMage = new BlackMage("Gargamel", 220, 30, 40, 200)
    val wMage = new WhiteMage("Saruman", 220, 30, 40, 200)
    val thunder = new Thunder
    val heal = new Heal
    bMage.changeWeapon(Some(wand))
    wMage.changeWeapon(Some(wand2))
    val enemy = new ConcreteEnemy("Entity1", 100, 10, 20, 10)
    val ally = new Warrior("Dummy", 10, 10, 10)
    ally.receiveDamage(100)
    enemy.receiveDamage(300)
    intercept[ProhibitedTarget]{
      bMage.castSpell(enemy, thunder)
    }
    intercept[ProhibitedTarget]{
      wMage.castSpell(ally, heal)
    }
    bMage.unequipWeapon()
    wMage.unequipWeapon()


  }








}
