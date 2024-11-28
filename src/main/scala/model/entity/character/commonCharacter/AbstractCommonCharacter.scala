package model.entity.character.commonCharacter

import controller.visitor.ActionVisitor
import model.entity.character.{AbstractCharacter, Character}

/**
 * An abstract common character extending AbstractCharacter and implementing Character traits.
 *
 * This class serves as a base for common character types in the game, providing basic character
 * functionalities and allowing interaction with ActionVisitors for performing actions.
 *
 * @param nameI The name of the character.
 * @param hit_pointsI The maximum hit points of the character.
 * @param defenseI The defense level of the character.
 * @param weightI The weight of the character.
 */
abstract class AbstractCommonCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCharacter(nameI, hit_pointsI, defenseI, weightI) with Character {

  /**
   * Accepts a visitor to perform actions specific to a regular character.
   *
   * This method implements the visitor pattern to allow ActionVisitors to interact with
   * regular character instances.
   *
   * @param visitor The ActionVisitor instance visiting this regular character.
   */
  def accept(visitor: ActionVisitor): Unit = visitor.visitRegularCharacter(this)

}
