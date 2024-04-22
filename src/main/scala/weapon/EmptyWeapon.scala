package weapon

import entity.character.Character

class EmptyWeapon(val _name: String = "Empty",
                  val _attackPoints: Int = 0,
                  val _weight: Int = 0,
                  var _owner: Character) extends AbstractWeapon {
  val _weapon_type : String = "Empty"

}
