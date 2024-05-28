package entity.character
import entity.Entity
import entity.enemy.Enemy
import magic.{BlackMagic, DefensiveSpell, Magic, OffensiveSpell, WhiteMagic}
import weapon.Weapon

class BlackMage (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractMagicCharacter (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int) {
  val _typeName : String = "BlackMage"

  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }

  def doSpellOnEnemy[T<:Enemy, S<: BlackMagic with OffensiveSpell](target:T, spell:S): Unit = {}
  def doSpellOnCharacter[T<:Character, S<: BlackMagic with DefensiveSpell](target:T, spell:S): Unit = {}
}
