package magic

import entity.character.WhiteMage

abstract class AbstractWhiteMagic extends AbstractMagic with WhiteMagic {
  override def checkSorcerer(sorcerer:WhiteMage): Unit = {}
}
