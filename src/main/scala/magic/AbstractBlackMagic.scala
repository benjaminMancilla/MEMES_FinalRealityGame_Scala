package magic

import entity.character.BlackMage

abstract class AbstractBlackMagic extends AbstractMagic with BlackMagic{
  override def checkSorcerer(sorcerer:BlackMage): Unit = {}
}
