package weapon
import character.Character

class Staff (val _name: String,
             val _attackPoints: Int,
             val _weight: Int,
             var _owner: Character,
             val _magicAttack: Int) extends AbstractMagicWeapon {

}
