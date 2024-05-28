package entity.character

import entity.enemy.Enemy
import magic.{BlackMagic, DefensiveSpell, OffensiveSpell, WhiteMagic}
import weapon.Weapon

class WhiteMage (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractMagicCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int) {
  val _typeName : String = "WhiteMage"

  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }
  def doSpellOnEnemy[T<:Enemy, S<: WhiteMagic with OffensiveSpell](target:T, spell:S): Unit = {}
  def doSpellOnCharacter[T<:Character, S<: WhiteMagic with DefensiveSpell](target:T, spell:S): Unit = {}


}
