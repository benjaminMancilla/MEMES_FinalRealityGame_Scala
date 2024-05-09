package entity.character

import entity.{AbstractEntity, Entity}
import weapon.{EmptyWeapon, Weapon}
//Usar Object Initialization pdf 07
//Usar truquito del trait para visibility del pdf 7 para el controlador mas tarde
abstract class AbstractCharacter extends AbstractEntity with Character  {

  val _isPlayer: Boolean = true

  var _equipped_weapon: Weapon = new EmptyWeapon(_owner = None)

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

  def checkValidWeapon(newWeapon: Weapon): Boolean

  override def doAttack(entity: Entity, damage: Int): Unit = {
    if (!equipped_weapon.isEmpty) {
      doDamage(entity: Entity, damage: Int)
    }
    else {
      printf(s"$name no tiene arma equipada, no puede atacar")
    }

  }



}

