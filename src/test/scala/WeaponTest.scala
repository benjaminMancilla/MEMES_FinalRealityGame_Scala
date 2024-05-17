import entity.character.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}
import munit.FunSuite
import weapon.{Axe, Bow, EmptyWeapon, Sword}
class WeaponTest extends FunSuite {

  test("AbstractWeapon should not initialize with invalid parameters"){
    val axe = new Axe("", -1, -20) : Axe
    assert(axe.name == "Weapon")
    assert(axe.attackPoints == 10)
    assert(axe.weight == 10)
  }

  test("Weapons should not be empty weapon (except empty weapon"){
    val axe = new Axe("Axe", 10, 20) : Axe
    assert(!axe.isEmptyWeapon)
    val empty = new EmptyWeapon()
    assert(empty.isEmptyWeapon)
  }

  test("AbstractWeapon should initialize correctly") {
    val axe = new Axe("Battle Axe", 60, 20) : Axe
    assert(axe.name == "Battle Axe")
    assert(axe.attackPoints == 60)
    assert(axe.weight == 20)
    assert(axe.owner.isEmpty)

  }

  test("AbstractWeapon should update owner correctly") {
    val character = new Warrior("Knight", 200, 30, 50)
    val sword = new Sword("Broadsword", 50, 15)
    character.changeWeapon(sword)
    assert(sword.owner.exists(_.name == "Knight"))
  }

  test("Axe should initialize correctly") {
    val axe = new Axe("War Axe", 70, 25) : Axe
    assert(axe.name == "War Axe")
    assert(axe.attackPoints == 70)
    assert(axe.weight == 25)
    assert(axe.owner.isEmpty)
  }

  test("Sword should initialize correctly") {
    val sword = new Sword("Longsword", 55, 18) : Sword
    assert(sword.name == "Longsword")
    assert(sword.attackPoints == 55)
    assert(sword.weight == 18)
    assert(sword.owner.isEmpty)
  }

  test("Bow should initialize correctly") {
    val bow = new Bow("Composite Bow", 40, 12) : Bow
    assert(bow.name == "Composite Bow")
    assert(bow.attackPoints == 40)
    assert(bow.weight == 12)
    assert(bow.owner.isEmpty)
  }

  test("Weapons should accept the correct Characters") {
    val axe = new Axe("Axe", 10, 10)
    val bow = new Bow("Bow", 10, 10)
    val sword = new Sword("Sword", 10, 10)
    val emptyW = new EmptyWeapon()

    val warrior = new Warrior("Entity1", 100, 10, 20)
    val ninja = new Ninja("Entity1", 100, 10, 20)
    val paladin = new Paladin("Entity1", 100, 10, 20)
    val blackMage = new BlackMage("Entity1", 100, 10, 20, 10)
    val whiteMage = new WhiteMage("Entity1", 100, 10, 20, 10)

    //axe
    assert(axe.canBeEquipped(warrior))
    assert(!axe.canBeEquipped(ninja))
    assert(axe.canBeEquipped(paladin))
    assert(!axe.canBeEquipped(blackMage))
    assert(!axe.canBeEquipped(whiteMage))

    //bow
    assert(bow.canBeEquipped(warrior))
    assert(bow.canBeEquipped(ninja))
    assert(!bow.canBeEquipped(paladin))
    assert(!bow.canBeEquipped(blackMage))
    assert(bow.canBeEquipped(whiteMage))

    //sword
    assert(sword.canBeEquipped(warrior))
    assert(sword.canBeEquipped(ninja))
    assert(sword.canBeEquipped(paladin))
    assert(sword.canBeEquipped(blackMage))
    assert(!sword.canBeEquipped(whiteMage))

    //empty
    assert(emptyW.canBeEquipped(warrior))
    assert(emptyW.canBeEquipped(ninja))
    assert(emptyW.canBeEquipped(paladin))
    assert(emptyW.canBeEquipped(blackMage))
    assert(emptyW.canBeEquipped(whiteMage))

  }
}

