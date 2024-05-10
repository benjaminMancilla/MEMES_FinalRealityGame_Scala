package entity.character

import weapon.Weapon

class WhiteMage (name: String, hit_points: Int, defense: Int, weight: Int, magic_points: Int)
  extends AbstractMagicCharacter(name: String, hit_points: Int, defense: Int, weight: Int, magic_points: Int) {
  val _typeName : String = "WhiteMage"

  def checkValidWeapon(newWeapon: Weapon): Boolean = {
    newWeapon.canBeEquipped(this)
  }

}
