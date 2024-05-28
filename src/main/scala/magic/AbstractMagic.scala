package magic

import exceptions.NonMagicWeaponException
import weapon.{MagicWeapon, Weapon}

abstract class AbstractMagic extends Magic{

  val _name: String
  val _manaCost: Int


  def name: String = _name
  def manaCost: Int = _manaCost

}
