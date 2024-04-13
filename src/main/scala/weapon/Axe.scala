package weapon
import character.Character

class Axe  (val _name: String,
            val _attackPoints: Int,
            val _weight: Int,
            var _owner: Character) extends AbstractWeapon {

}
