package entity.character

import weapon.Weapon

class Warrior (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int) {
  val _typeName : String = "Warrior"

  def checkValidWeapon(newWeapon: Weapon): Boolean = {
    newWeapon.canBeEquipped(this)
  }

}
