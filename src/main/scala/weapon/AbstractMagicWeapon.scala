package weapon

abstract class AbstractMagicWeapon(nameI: String, attackPointsI: Int, weightI: Int, magicAttackI: Int)
  extends AbstractWeapon(nameI: String, attackPointsI: Int, weightI: Int) with MagicWeapon {
  private val _magicAttack: Int = try {
    if (magicAttackI>=0) magicAttackI else throw new IllegalArgumentException("Weapon magic attack can not be negative.")
  } catch {
    case _: IllegalArgumentException => 0
  }
  def magicAttack: Int = _magicAttack

}
