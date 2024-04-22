package entity.character

import weapon.Weapon

class Warrior (val _name: String,
               val _hit_points: Int,
               val _defense: Int,
               val _weight: Int,
               var _equipped_weapon: Weapon)
  extends AbstractCharacter {
  override val _type_name: String = "Warrior"
  override val _weapon_list: List[String] = List("Sword", "Axe", "Bow")



}
