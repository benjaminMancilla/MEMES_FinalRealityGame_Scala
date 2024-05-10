package entity.character

import weapon.Weapon

class BlackMage (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractMagicCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int) {
  val _typeName : String = "BlackMage"

  def checkValidWeapon(newWeapon: Weapon): Boolean = {
    newWeapon.canBeEquipped(this)
  }
}
