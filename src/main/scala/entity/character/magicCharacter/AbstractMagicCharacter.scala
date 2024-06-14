package entity.character.magicCharacter

import entity.Entity
import entity.character.AbstractCharacter
import exceptions.Require
import exceptions.entityE.ProhibitedTarget
import exceptions.magicE.{InvalidMagicType, NoMagicPoints}
import exceptions.weaponE.EmptyWeaponException
import magic.Magic


/**
 * Abstract class representing a magicE character entityE in a game.
 * This class extends the AbstractCharacter class and implements the MagicCharacter trait.
 *
 * @param nameI          The name of the magicE character.
 * @param hit_pointsI    The initial hit points of the magicE character.
 * @param defenseI       The defense level of the magicE character.
 * @param weightI        The weight of the magicE character.
 * @param magic_pointsI  The total magicE points of the magicE character.
 */
abstract class AbstractMagicCharacter(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, magic_pointsI: Int)
  extends AbstractCharacter(nameI, hit_pointsI, defenseI, weightI) with MagicCharacter {

  /**
   * The total magicE points of the magicE character.
   */
  private val _magic_points: Int = {Require.Stat(magic_pointsI, "magic_points") in (0 to 10000)}

  /**
   * The current magicE points of the magicE character.
   */
  private var _current_magic_points: Int = _magic_points

  /**
   * Gets the total magicE points of the magicE character.
   *
   * @return The total magicE points of the magicE character.
   */
  def magicPoints: Int = _magic_points

  /**
   * Gets the current magicE points of the magicE character.
   *
   * @return The current magicE points of the magicE character.
   */
  def currentMagicPoints: Int = _current_magic_points

  /**
   * Sets the current magicE points of the magicE character.
   *
   * @param newMp The new value for the current magicE points.
   */
  def currentMagicPoints_=(newMp: Int): Unit = {
    _current_magic_points = newMp
  }

  /**
   * Casts a spell on a target entityE.
   *
   * @param target The target entityE on which the spell is cast.
   * @param spell The spell to be cast.
   */
  def castSpell(target:Entity, spell: Magic): Unit = {
    checkSpell(spell)
    spell.checkTarget(target)
    if (currentMagicPoints < spell.manaCost) {
      throw new NoMagicPoints("No enough magicE points")
    }
    if (equipped_weapon.isEmpty) {
      throw new EmptyWeaponException("Can not cast a spell with no weaponE")
    }
    if (!target.state) {
      throw new ProhibitedTarget(s"${target.name} is dead, can not cast a spell on it")
    }
    val mDmg: Int = equipped_weapon.map(_.magicAttack).getOrElse(0)
    spell.applySpell(this,target,mDmg)
    currentMagicPoints -= spell.manaCost

  }

  /**
   * Checks if the magicE character can cast the given spell.
   *
   * @param spell The spell to be cast.
   */
  def checkSpell(spell:Magic): Unit = {
    throw new InvalidMagicType(s"${spell.name} spell is not compatible with ${this.name}")
  }




}

