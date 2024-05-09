package entity.character

import weapon.Weapon

class BlackMage (val _name: String,
                 val _hit_points: Int,
                 val _defense: Int,
                 val _weight: Int,
                 val _magic_points: Int)
  extends AbstractMagicCharacter {

  def checkValidWeapon(newWeapon: Weapon): Boolean = {
    newWeapon.canBeEquipped(this)
  }
}
