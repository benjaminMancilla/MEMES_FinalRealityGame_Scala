package entity.character

trait MagicCharacter extends Character with PIMagicPlayer {
  def currentMagicPoints_=(newMp: Int) : Unit

}
