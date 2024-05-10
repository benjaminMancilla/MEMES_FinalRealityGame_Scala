package entity.character

import entity.{AbstractEntity, Entity}
import weapon.{EmptyWeapon, Weapon}

abstract class AbstractCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int)
  extends AbstractEntity(nameI: String, hit_pointsI: Int, defenseI: Int,weightI: Int) with Character  {


  protected val _typeName: String

  protected val _isPlayer: Boolean = true

  protected var _equipped_weapon: Weapon = new EmptyWeapon()

  def equipped_weapon: Weapon = _equipped_weapon
  def equipped_weapon_=(new_weapon: Weapon): Unit = {
    _equipped_weapon = new_weapon
  }

  def barValue: Int = {
    weight + equipped_weapon.weight/2
  }

  def changeWeapon(newWeapon: Weapon): Unit = {
    if (checkValidWeapon(newWeapon: Weapon)) {
      if(newWeapon.owner.isDefined){
        println(s"${newWeapon.name} ya tiene dueño.")
        return
      }
      equipped_weapon.owner = None
      _equipped_weapon = newWeapon
      newWeapon.owner = Some(this)
    } else {
      println(s"No puedes equipar ${newWeapon.name} en este personaje.")
    }
  }

  protected def checkValidWeapon(newWeapon: Weapon): Boolean

  override def doAttack(entity: Entity, damage: Int): Unit = {
    if (!equipped_weapon.hasOwner) {
      doDamage(entity: Entity, damage: Int)
    }
    else {
      printf(s"$name no tiene arma equipada, no puede atacar")
    }

  }

  def typeName: String = _typeName


}

