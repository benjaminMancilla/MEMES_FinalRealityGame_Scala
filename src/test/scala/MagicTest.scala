import entity.character.magicCharacter.{BlackMage, WhiteMage}
import entity.enemy.ConcreteEnemy
import exceptions.magic.{InvalidMagicType, InvalidSpellTarget}
import magic.blackMagic.Fire
import magic.whiteMagic.{Heal, Paralyze, Poison, Thunder}
import weapon.magicWeapon.{Staff, Wand}

import java.io.ByteArrayOutputStream




class MagicTest extends munit.FunSuite{
  object TestUtils {
    def captureOutput(block: => Unit): String = {
      val outputStream = new ByteArrayOutputStream()
      Console.withOut(outputStream) {
        block
      }
      outputStream.toString
    }

    def noExceptionThrown(block: => Unit): Boolean = {
      try {
        block
        true
      } catch {
        case _: Throwable => false
      }
    }
  }
  val fire = new Fire
  val thunder = new Thunder
  val heal = new Heal
  val poison = new Poison
  val para = new Paralyze
  val blackMage = new BlackMage("Black", 100, 10, 10, 10000)
  val whiteMage = new WhiteMage("White", 100, 10, 10, 10000)
  val wand = new Wand("Wand", 10, 10, 10)
  val staff = new Staff("Staff", 10, 10, 10)
  blackMage.changeWeapon(Some(wand))
  whiteMage.changeWeapon(Some(staff))

  test("Magic should initialize correctly"){
    assert(fire.name == "Fire")
    assert(fire.manaCost == 15)
    assert(thunder.name == "Thunder")
    assert(thunder.manaCost == 20)
    assert(heal.name == "Heal")
    assert(heal.manaCost == 15)
    assert(poison.name == "Poison")
    assert(poison.manaCost == 30)
    assert(para.name == "Paralyze")
    assert(para.manaCost == 25)
  }

  test("Fire spell effect"){
    val target = new ConcreteEnemy("Dummy", 100, 10, 10, 10)
    fire.applySpell(blackMage, target, 20)
    assert(target.current_hit_points == 90)
  }
  test("Thunder spell effect"){
    val target = new ConcreteEnemy("Dummy", 100, 10, 10, 10)
    thunder.applySpell(blackMage, target, 20)
    assert(target.current_hit_points == 90)
  }
  test("Heal spell effect"){
    blackMage.receiveDamage(60)
    heal.applySpell(whiteMage, blackMage, 20)
    println(100-50 + (100*30/100))
    assert(blackMage.current_hit_points == 100-50 + (100*30/100))
  }
  test("Poison spell effect"){
    val target = new ConcreteEnemy("Dummy", 100, 10, 10, 10)
    //Temporary while effect has not been implemented
    val output = TestUtils.captureOutput {
      poison.applySpell(whiteMage, target, 10)
    }
    assert(output.contains("Poison"))
    //Temporary while effect has not been implemented

  }
  test("Paralyze spell effect"){
    val target = new ConcreteEnemy("Dummy", 100, 10, 10, 10)
    //Temporary while effect has not been implemented
    val output = TestUtils.captureOutput {
      para.applySpell(whiteMage, target, 10)
    }
    assert(output.contains("Paralyzed"))
    //Temporary while effect has not been implemented


  }

  test("BlackMagic checks"){
    val target = new ConcreteEnemy("Dummy", 10000, 10, 10, 10)
    val fireBool = TestUtils.noExceptionThrown {
      blackMage.castSpell(target, fire)
    }
    val thunderBool = TestUtils.noExceptionThrown {
      blackMage.castSpell(target, thunder)
    }
    assert(fireBool)
    assert(thunderBool)
    intercept[InvalidMagicType]{
      whiteMage.castSpell(target, fire)
    }
    intercept[InvalidMagicType]{
      whiteMage.castSpell(target, thunder)
    }

  }

  test("WhiteMagic checks") {
    val target = new ConcreteEnemy("Dummy", 10000, 10, 10, 10)
    val healBool = TestUtils.noExceptionThrown {
      whiteMage.castSpell(blackMage, heal)
    }
    val poisonBool = TestUtils.noExceptionThrown {
      whiteMage.castSpell(target, poison)
    }
    val paraBool = TestUtils.noExceptionThrown {
      whiteMage.castSpell(target, para)
    }
    assert(healBool)
    assert(poisonBool)
    assert(paraBool)
    intercept[InvalidMagicType] {
      blackMage.castSpell(whiteMage, heal)
    }
    intercept[InvalidMagicType] {
      blackMage.castSpell(target, poison)
    }
    intercept[InvalidMagicType] {
      blackMage.castSpell(target, para)
    }

  }

  test("Offensive spells can only target enemies"){
    val target = new ConcreteEnemy("Dummy", 10000, 10, 10, 10)
    val bool = TestUtils.noExceptionThrown {
      whiteMage.castSpell(target, para)
      whiteMage.castSpell(target, poison)
      blackMage.castSpell(target, fire)
      blackMage.castSpell(target, thunder)
    }
    assert(bool)
    intercept[InvalidSpellTarget]{
      whiteMage.castSpell(blackMage, para)
    }
    intercept[InvalidSpellTarget]{
      whiteMage.castSpell(blackMage, poison)
    }
    intercept[InvalidSpellTarget]{
      blackMage.castSpell(whiteMage, fire)
    }
    intercept[InvalidSpellTarget]{
      blackMage.castSpell(whiteMage, thunder)
    }

  }

  test("Defensive spells can only target Characters"){
    val target = new ConcreteEnemy("Dummy", 10000, 10, 10, 10)
    val bool = TestUtils.noExceptionThrown {
      whiteMage.castSpell(blackMage, heal)
    }
    assert(bool)
    intercept[InvalidSpellTarget]{
      whiteMage.castSpell(target, heal)
    }

  }



}

