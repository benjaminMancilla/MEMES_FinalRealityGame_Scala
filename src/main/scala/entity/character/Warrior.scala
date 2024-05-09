package entity.character

import weapon.{EmptyWeapon, Weapon}

class Warrior (val _name: String,
               val _hit_points: Int,
               val _defense: Int,
               val _weight: Int)
  extends AbstractCharacter {

  def checkValidWeapon(newWeapon: Weapon): Boolean = {
    newWeapon.canBeEquipped(this)
  }

}
