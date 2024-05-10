package weapon

abstract class AbstractMagicWeapon(name: String, attackPoints: Int, weight: Int, magicAttack: Int)
  extends AbstractWeapon(name: String, attackPoints: Int, weight: Int) with MagicWeapon {
  private val _magicAttack: Int = magicAttack
  def magicAttack: Int = _magicAttack

}
