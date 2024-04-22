package entity.character

import weapon.Weapon

class WhiteMage (val _name: String,
                 val _hit_points: Int,
                 val _defense: Int,
                 val _weight: Int,
                 var _equipped_weapon: Weapon,
                 val _magic_points: Int)
  extends AbstractMagicCharacter {
  override val _type_name: String = "WhiteMage"
  override val _weapon_list: List[String] = List("Bow", "Wand", "Staff")


}
