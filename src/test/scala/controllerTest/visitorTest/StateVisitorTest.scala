package controllerTest.visitorTest

import controller.visitor.StateVisitor
import model.entity.character.Character
import model.entity.character.commonCharacter.Warrior
import model.entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import model.entity.enemy.{ConcreteEnemy, Enemy}

class StateVisitorTest extends munit.FunSuite {

  test("StateVisitor should set buffer to E1 for an enemy with active state") {
    val enemy: Enemy = new ConcreteEnemy("Goblin", 50, 10, 20, 15)
    enemy.state = true
    val visitor = new StateVisitor()

    visitor.visitEnemy(enemy)
    assertEquals(visitor.buffer, List("E1"))
  }

  test("StateVisitor should set buffer to E0 for an enemy with inactive state") {
    val enemy: Enemy = new ConcreteEnemy("Goblin", 50, 10, 20, 15)
    enemy.state = false
    val visitor = new StateVisitor()

    visitor.visitEnemy(enemy)
    assertEquals(visitor.buffer, List("E0"))
  }

  test("StateVisitor should set buffer to C1 for a regular character with active state") {
    val character: Character = new Warrior("Warrior", 100, 30, 60)
    character.state = true
    val visitor = new StateVisitor()

    visitor.visitRegularCharacter(character)
    assertEquals(visitor.buffer, List("C1"))
  }

  test("StateVisitor should set buffer to C0 for a regular character with inactive state") {
    val character: Character = new Warrior("Warrior", 100, 30, 60)
    character.state = false
    val visitor = new StateVisitor()

    visitor.visitRegularCharacter(character)
    assertEquals(visitor.buffer, List("C0"))
  }

  test("StateVisitor should set buffer to C1 for a magic character with active state") {
    val character: MagicCharacter = new BlackMage("Mage", 60, 10, 20, 100)
    character.state = true
    val visitor = new StateVisitor()

    visitor.visitMagicCharacter(character)
    assertEquals(visitor.buffer, List("C1"))
  }

  test("StateVisitor should set buffer to C0 for a magic character with inactive state") {
    val character: MagicCharacter = new BlackMage("Mage", 60, 10, 20, 100)
    character.state = false
    val visitor = new StateVisitor()

    visitor.visitMagicCharacter(character)
    assertEquals(visitor.buffer, List("C0"))
  }

  test("StateVisitor should set buffer to C1 for a black mage with active state") {
    val character = new BlackMage("Black Mage", 60, 10, 20, 100)
    character.state = true
    val visitor = new StateVisitor()

    visitor.visitBlackMagicCharacter(character)
    assertEquals(visitor.buffer, List("C1"))
  }

  test("StateVisitor should set buffer to C0 for a black mage with inactive state") {
    val character: BlackMage = new BlackMage("Black Mage", 60, 10, 20, 100)
    character.state = false
    val visitor = new StateVisitor()

    visitor.visitBlackMagicCharacter(character)
    assertEquals(visitor.buffer, List("C0"))
  }

  test("StateVisitor should set buffer to C1 for a white mage with active state") {
    val character: WhiteMage = new WhiteMage("White Mage", 60, 10, 20, 100)
    character.state = true
    val visitor = new StateVisitor()

    visitor.visitWhiteMagicCharacter(character)
    assertEquals(visitor.buffer, List("C1"))
  }

  test("StateVisitor should set buffer to C0 for a white mage with inactive state") {
    val character: WhiteMage = new WhiteMage("White Mage", 60, 10, 20, 100)
    character.state = false
    val visitor = new StateVisitor()

    visitor.visitWhiteMagicCharacter(character)
    assertEquals(visitor.buffer, List("C0"))
  }
}
