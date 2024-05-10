package entity.character

import entity.PIEntity
import weapon.Weapon

trait PICharacter extends PIEntity{
  def equipped_weapon: Weapon
  def typeName: String

}
