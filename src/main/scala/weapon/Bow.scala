package weapon

import entity.character.Character

class Bow (val _name: String,
           val _attackPoints: Int,
           val _weight: Int,
           var _owner: Character) extends AbstractWeapon {
  val _weapon_type : String = "Bow"

}
