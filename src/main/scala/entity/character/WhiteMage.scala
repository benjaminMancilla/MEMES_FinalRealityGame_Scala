package entity.character

import entity.Entity
import entity.enemy.Enemy
import magic.{BlackMagic, DefensiveSpell, Magic, OffensiveSpell, WhiteMagic}
import weapon.Weapon

class WhiteMage (nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractMagicCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int) {
  val _typeName : String = "WhiteMage"

  def checkValidWeapon(newWeapon: Option[Weapon]): Boolean = {
    newWeapon.exists(_.canBeEquipped(this))
  }

  override def checkSpell(spell:Magic): Unit = {
    spell.checkSorcerer(this)
  }



}
