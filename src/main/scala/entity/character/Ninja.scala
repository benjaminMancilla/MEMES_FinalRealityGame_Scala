package entity.character

import weapon.Weapon

class Ninja (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int) {
  val _typeName : String = "Ninja"

  def checkValidWeapon(newWeapon: Weapon): Boolean = {
    newWeapon.canBeEquipped(this)
  }


}