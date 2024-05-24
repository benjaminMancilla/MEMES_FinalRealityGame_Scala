package entity.character

import weapon.Weapon

class Paladin (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int) {
  val _typeName : String = "Paladin"

  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }
}