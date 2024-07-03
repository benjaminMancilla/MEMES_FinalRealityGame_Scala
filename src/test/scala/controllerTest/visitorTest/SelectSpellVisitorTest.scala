package controllerTest.visitorTest

import controller.visitor.SelectSpellVisitor
import entity.character.Character
import entity.character.commonCharacter.Warrior
import entity.character.magicCharacter.{BlackMage, MagicCharacter, WhiteMage}
import entity.enemy.{ConcreteEnemy, Enemy}
import exceptions.stateE.InvalidSpellSelector


class SelectSpellVisitorTest extends munit.FunSuite {

  test("SelectSpellVisitor should throw InvalidSpellSelector for Enemy") {
    val enemy: Enemy = new ConcreteEnemy("Goblin", 50, 10, 20, 15)
    val visitor = new SelectSpellVisitor()

    interceptMessage[InvalidSpellSelector](s"${enemy.name} is an enemy, does not have magic.") {
      visitor.visitEnemy(enemy)
    }
  }

  test("SelectSpellVisitor should throw InvalidSpellSelector for RegularCharacter") {
    val character: Character = new Warrior("Warrior", 100, 30, 60)
    val visitor = new SelectSpellVisitor()

    interceptMessage[InvalidSpellSelector](s"${character.name} is a common character, does not have magic.") {
      visitor.visitRegularCharacter(character)
    }
  }

  test("SelectSpellVisitor should throw InvalidSpellSelector for MagicCharacter") {
    val character: MagicCharacter = new BlackMage("Generic Mage", 60, 10, 20, 100)
    val visitor = new SelectSpellVisitor()

    interceptMessage[InvalidSpellSelector](s"${character.name} is a unknown magic character, does not known have magic.") {
      visitor.visitMagicCharacter(character)
    }
  }

  test("SelectSpellVisitor should set buffer correctly for BlackMagicCharacter") {
    val character: BlackMage = new BlackMage("Black Mage", 60, 10, 20, 100)
    val visitor = new SelectSpellVisitor()

    visitor.visitBlackMagicCharacter(character)
    assertEquals(visitor.buffer, List("Fire", "Thunder"))
  }

  test("SelectSpellVisitor should set buffer correctly for WhiteMagicCharacter") {
    val character: WhiteMage = new WhiteMage("White Mage", 60, 10, 20, 100)
    val visitor = new SelectSpellVisitor()

    visitor.visitWhiteMagicCharacter(character)
    assertEquals(visitor.buffer, List("Poison", "Paralyze", "Heal"))
  }
}

