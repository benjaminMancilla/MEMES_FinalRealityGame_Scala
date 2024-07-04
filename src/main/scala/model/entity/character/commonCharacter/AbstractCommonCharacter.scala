package model.entity.character.commonCharacter

import controller.visitor.ActionVisitor
import model.entity.character.{AbstractCharacter, Character}

abstract class AbstractCommonCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCharacter(nameI, hit_pointsI, defenseI, weightI) with Character {

  def accept(visitor: ActionVisitor): Unit = visitor.visitRegularCharacter(this)


}
