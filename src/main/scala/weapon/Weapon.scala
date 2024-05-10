package weapon

import entity.character.Character
trait Weapon extends PIWeapon {
  def owner_=(newOwner: Option[Character]): Unit
  def setHasOwner(newBool: Boolean): Unit
}
