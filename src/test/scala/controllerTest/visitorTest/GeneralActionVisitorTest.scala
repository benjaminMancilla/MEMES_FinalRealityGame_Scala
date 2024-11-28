package controllerTest.visitorTest

import controller.visitor.GeneralActionVisitor
import model.entity.character.Character
import model.entity.character.commonCharacter.Warrior
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.{ConcreteEnemy, Enemy}
import munit.FunSuite

class GeneralActionVisitorTest extends FunSuite {

  test("visitEnemy should set buffer to empty list") {
    val visitor = new GeneralActionVisitor()
    val enemy: Enemy = new ConcreteEnemy("Goblin", 50, 10, 20, 15)

    visitor.visitEnemy(enemy)

    assertEquals(visitor.buffer, List.empty[String])
  }

  test("visitRegularCharacter should set buffer with expected actions") {
    val visitor = new GeneralActionVisitor()
    val character: Character = new Warrior("Warrior", 100, 30, 60)

    visitor.visitRegularCharacter(character)

    assertEquals(visitor.buffer, List("Attack", "Weapon", "Pass"))
  }

  test("visitMagicCharacter should set buffer with expected actions") {
    val visitor = new GeneralActionVisitor()
    val character: MagicCharacter = new BlackMage("Black Mage", 60, 10, 20, 100)

    visitor.visitMagicCharacter(character)

    assertEquals(visitor.buffer, List("Attack", "Weapon", "Pass", "Magic"))
  }

  test("visitBlackMagicCharacter should set buffer with expected actions") {
    val visitor = new GeneralActionVisitor()
    val blackMage: BlackMage = new BlackMage("Dark Mage", 60, 10, 20, 100)

    visitor.visitBlackMagicCharacter(blackMage)

    assertEquals(visitor.buffer, List("Attack", "Weapon", "Pass", "Magic"))
  }

  test("visitWhiteMagicCharacter should set buffer with expected actions") {
    val visitor = new GeneralActionVisitor()
    val whiteMage: WhiteMage = new WhiteMage("Light Mage", 60, 10, 20, 100)

    visitor.visitWhiteMagicCharacter(whiteMage)

    assertEquals(visitor.buffer, List("Attack", "Weapon", "Pass", "Magic"))
  }

}
