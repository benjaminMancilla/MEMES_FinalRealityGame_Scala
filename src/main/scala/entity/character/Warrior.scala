package entity.character

import weapon.Weapon

class Warrior (name: String, hit_points: Int, defense: Int, weight: Int)
  extends AbstractCharacter(name: String, hit_points: Int, defense: Int, weight: Int) {
  val _typeName : String = "Warrior"

  def checkValidWeapon(newWeapon: Weapon): Boolean = {
    newWeapon.canBeEquipped(this)
  }

}
