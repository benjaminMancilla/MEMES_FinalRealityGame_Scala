package weapon

abstract class AbstractMagicWeapon extends AbstractWeapon with MagicWeapon {
  def magicAttack: Int = _magicAttack

}
