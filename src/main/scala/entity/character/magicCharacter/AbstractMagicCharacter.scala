package entity.character.magicCharacter

import entity.Entity
import entity.character.AbstractCharacter
import exceptions.Require
import exceptions.entity.ProhibitedTarget
import exceptions.magic.{InvalidMagicType, NoMagicPoints}
import exceptions.weapon.EmptyWeaponException
import magic.Magic


/**
 * Abstract class representing a magic character entity in a game.
 * This class extends the AbstractCharacter class and implements the MagicCharacter trait.
 *
 * @param nameI          The name of the magic character.
 * @param hit_pointsI    The initial hit points of the magic character.
 * @param defenseI       The defense level of the magic character.
 * @param weightI        The weight of the magic character.
 * @param magic_pointsI  The total magic points of the magic character.
 */
abstract class AbstractMagicCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractCharacter(nameI, hit_pointsI, defenseI, weightI) with MagicCharacter {

  /**
   * The total magic points of the magic character.
   */
  private val _magic_points: Int = {Require.Stat(magic_pointsI, "magic_points") in (0 to 10000)}

  /**
   * The current magic points of the magic character.
   */
  private var _current_magic_points: Int = _magic_points

  /**
   * Gets the total magic points of the magic character.
   *
   * @return The total magic points of the magic character.
   */
  def magicPoints: Int = _magic_points

  /**
   * Gets the current magic points of the magic character.
   *
   * @return The current magic points of the magic character.
   */
  def currentMagicPoints: Int = _current_magic_points

  /**
   * Sets the current magic points of the magic character.
   *
   * @param newMp The new value for the current magic points.
   */
  def currentMagicPoints_=(newMp: Int): Unit = {
    _current_magic_points = newMp
  }

  /**
   * Casts a spell on a target entity.
   *
   * @param target The target entity on which the spell is cast.
   * @param spell The spell to be cast.
   */
  def castSpell(target:Entity, spell: Magic): Unit = {
    checkSpell(spell)
    spell.checkTarget(target)
    if (currentMagicPoints < spell.manaCost) {
      throw new NoMagicPoints("No enough magic points")
    }
    if (equipped_weapon.isEmpty) {
      throw new EmptyWeaponException("Can not cast a spell with no weapon")
    }
    if (!target.state) {
      throw new ProhibitedTarget(s"${target.name} is dead, can not cast a spell on it")
    }
    val mDmg: Int = equipped_weapon.map(_.magicAttack).getOrElse(0)
    spell.applySpell(this,target,mDmg)
    currentMagicPoints -= spell.manaCost

  }

  /**
   * Checks if the magic character can cast the given spell.
   *
   * @param spell The spell to be cast.
   */
  def checkSpell(spell:Magic): Unit = {
    throw new InvalidMagicType(s"${spell.name} spell is not compatible with ${this.name}")
  }




}

