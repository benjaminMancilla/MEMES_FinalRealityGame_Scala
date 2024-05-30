package entity

import exceptions.Require
import magic.{DefensiveSpell, OffensiveSpell}
import exceptions.HealingDeadEntity
import exceptions.InvalidSpellTarget

/**
 * Abstract class representing an entity in a game with basic properties and behavior.
 * This class extends the Entity trait and implements the PIEntity trait.
 * An entity can be a character controlled by the player or an enemy.
 *
 * @param nameI       The name of the entity.
 * @param hit_pointsI The initial hit points of the entity.
 * @param defenseI    The defense level of the entity.
 * @param weightI     The weight of the entity.
 */
abstract class AbstractEntity(nameI: String, hit_pointsI: Int, defenseI: Int, weightI:Int) extends Entity with PIEntity {

  /**
   * Name of the entity.
   */
  private val _name: String = {Require.Name(nameI) lengthAtLeast 3}

  /**
   * Hit points of the entity.
   */
  private val _hit_points: Int = {Require.Stat(hit_pointsI, "hit_points") in (1 to 10000)}


  /**
   * Defense level of the entity.
   */
  private val _defense: Int = {Require.Stat(defenseI, "defense") atLeast  0 }

  /**
   * Weight of the entity.
   */
  private val _weight: Int = {Require.Stat(weightI, "weight") atLeast  0 }


  /**
   * Indicates whether the entity is controlled by the player.
   * Must be implemented by subclasses.
   */
  protected val _isPlayer: Boolean

  /**
   * Current hit points of the entity.
   */
  private var _current_hit_points: Int = _hit_points

  /**
   * State of the entity (true for alive, false for dead).
   */
  private var _state: Boolean = true

  /**
   * Gets the name of the entity.
   *
   * @return The name of the entity.
   */
  def name: String = _name

  /**
   * Gets the hit points of the entity.
   *
   * @return The hit points of the entity.
   */
  def hit_points: Int = _hit_points

  /**
   * Gets the defense level of the entity.
   *
   * @return The defense level of the entity.
   */
  def defense: Int = _defense

  /**
   * Gets the weight of the entity.
   *
   * @return The weight of the entity.
   */
  def weight: Int = _weight

  /**
   * Gets the current hit points of the entity.
   *
   * @return The current hit points of the entity.
   */
  def current_hit_points: Int = _current_hit_points

  /**
   * Sets the current hit points of the entity.
   *
   * @param new_current_hit_points The new value for the current hit points.
   */
  def current_hit_points_=(new_current_hit_points: Int): Unit = {
    _current_hit_points = new_current_hit_points
  }

  /**
   * Gets the state of the entity.
   *
   * @return The state of the entity (true for alive, false for dead).
   */
  def state: Boolean = _state

  /**
   * Sets the state of the entity.
   *
   * @param new_character_state The new state of the entity (true for alive, false for dead).
   */
  def state_=(new_character_state: Boolean): Unit = {
    _state = new_character_state
  }

  /**
   * Performs an attack on another entity.
   *
   * @param entity The entity being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  def doDamage(entity: Entity, damage: Int): Unit = {
    if (!state) {
      printf(s"$name is unable to attack, already out of combat.")
    }
    else {
      println(s"$name attacks ${entity.name}")
      val extraDmg = entity.receiveDamage(damage)
      if (extraDmg > 0) {
        println(s"$name has defeated ${entity.name} with a $extraDmg of extra DMG!!!!!.")
      }
      else if (extraDmg == 0) {
        println(s"$name has defeated ${entity.name} precisely!!")
      }
      else {
        println(s"$name has attacked ${entity.name}")
      }
    }
  }
  /**
   * Receives damage from an attack.
   *
   * @param damage The amount of damage received.
   * @return The remaining hit points after receiving the damage.
   */
  def receiveDamage(damage: Int): Int = {
    val totalDmg = damage - defense
    var extraDmg = -1
    if (current_hit_points - totalDmg <= 0) {
      extraDmg = totalDmg - current_hit_points
      current_hit_points = 0
      state = false
    }
    else {
      printf(s"$name has received $totalDmg of DMG")
      current_hit_points -= totalDmg
      state = true
    }
    extraDmg
  }

  /**
   * Performs an attack on another entity.
   *
   * @param entity The entity being attacked.
   * @param damage The amount of damage to be inflicted.
   */
  def doAttack(entity: Entity, damage: Int): Unit = {
    doDamage(entity: Entity, damage: Int)
  }

  /**
   * Indicates whether the entity is controlled by the player or not.
   *
   * @return true if the entity is controlled by the player, false otherwise.
   */
  def isPlayer: Boolean = _isPlayer

  /**
   * Performs healing on another entity.
   *
   * @param entity The entity being healed.
   * @param heal The amount of healing to be performed.
   */
  def doHealing(entity: Entity, heal: Int): Unit = {
    doHeal(entity: Entity, heal: Int)
  }

  /**
   * Logic of healing on another entity.
   *
   * @param entity The entity performing the healing.
   * @param heal The amount of healing to be performed.
   */
  def doHeal(entity: Entity, heal: Int): Unit = {
    if (!state) {
      throw new HealingDeadEntity(s"${this.name} is dead, can not heal ${entity.name}")
    }
    else if (!entity.state){
      throw new HealingDeadEntity(s"${entity.name} is dead, can not be healed by ${this.name}")
    }
    else {
      println(s"$name heals ${entity.name}")
      val extraHeal = entity.receiveHealing(heal)
    }
  }

  /**
   * Receives healing from a spell or ability.
   *
   * @param heal The amount of healing received.
   * @return The remaining hit points after receiving the healing.
   */
  def receiveHealing(heal: Int): Int = {
    var extraHeal = -1
    if (current_hit_points + heal >= hit_points) {
      extraHeal = heal + current_hit_points - hit_points
      current_hit_points = hit_points
      state = true
    }
    else {
      printf(s"$name has received $heal of heal")
      current_hit_points += heal
      state = true
    }
    extraHeal
  }

  /**
   * Checks if the target entity is valid for an offensive spell.
   *
   * @param spell The offensive spell being cast.
   */
  def checkSpell(spell: OffensiveSpell): Unit = {
    throw new InvalidSpellTarget(s"${spell.name} is a offensive spell, can not be cast on ${this.name}")
  }
  /**
   * Checks if the target entity is valid for a defensive spell.
   *
   * @param spell The defensive spell being cast.
   */
  def checkSpell(spell: DefensiveSpell): Unit = {
    throw new InvalidSpellTarget(s"${spell.name} is a defensive spell, can not be cast on ${this.name}")
  }

}
