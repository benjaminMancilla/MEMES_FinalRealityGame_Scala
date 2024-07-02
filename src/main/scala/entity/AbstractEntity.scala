package entity

import effect.Effect
import exceptions.Require
import exceptions.effectE.RepeatedEffect
import magic.{DefensiveSpell, OffensiveSpell}
import exceptions.entityE.HealingDeadEntity
import exceptions.magicE.InvalidSpellTarget

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * Abstract class representing an entityE in a game with basic properties and behavior.
 * This class extends the Entity trait and implements the PIEntity trait.
 * An entityE can be a character controlled by the player or an enemy.
 *
 * @param nameI       The name of the entityE.
 * @param hit_pointsI The initial hit points of the entityE.
 * @param defenseI    The defense level of the entityE.
 * @param weightI     The weight of the entityE.
 */
abstract class AbstractEntity(nameI: String, hit_pointsI: Int, defenseI: Int, weightI:Int) extends Entity with PIEntity {

  /**
   * Name of the entityE.
   */
  private val _name: String = {Require.Name(nameI) lengthAtLeast 3}

  /**
   * Hit points of the entityE.
   */
  private val _hit_points: Int = {Require.Stat(hit_pointsI, "hit_points") in (1 to 10000)}


  /**
   * Defense level of the entityE.
   */
  private val _defense: Int = {Require.Stat(defenseI, "defense") atLeast  0 }

  /**
   * Weight of the entityE.
   */
  private val _weight: Int = {Require.Stat(weightI, "weight") atLeast  0 }


  /**
   * Indicates whether the entityE is controlled by the player.
   * Must be implemented by subclasses.
   */
  protected val _isPlayer: Boolean

  /**
   * Current hit points of the entityE.
   */
  private var _current_hit_points: Int = _hit_points

  /**
   * State of the entityE (true for alive, false for dead).
   */
  private var _state: Boolean = true

  /**
   * Current active effects, it can be empty.
   */
  private val _activeEffects: ListBuffer[Effect] = ListBuffer()

  private var _skipTurn: Boolean = false

  /**
   * Gets the name of the entityE.
   *
   * @return The name of the entityE.
   */
  def name: String = _name

  /**
   * Gets the hit points of the entityE.
   *
   * @return The hit points of the entityE.
   */
  def hit_points: Int = _hit_points

  /**
   * Gets the defense level of the entityE.
   *
   * @return The defense level of the entityE.
   */
  def defense: Int = _defense

  /**
   * Gets the weight of the entityE.
   *
   * @return The weight of the entityE.
   */
  def weight: Int = _weight

  /**
   * Gets the current hit points of the entityE.
   *
   * @return The current hit points of the entityE.
   */
  def current_hit_points: Int = _current_hit_points

  /**
   * Sets the current hit points of the entityE.
   *
   * @param new_current_hit_points The new value for the current hit points.
   */
  def current_hit_points_=(new_current_hit_points: Int): Unit = {
    _current_hit_points = new_current_hit_points
  }

  /**
   * Gets the state of the entityE.
   *
   * @return The state of the entityE (true for alive, false for dead).
   */
  def state: Boolean = _state

  /**
   * Sets the state of the entityE.
   *
   * @param new_character_state The new state of the entityE (true for alive, false for dead).
   */
  def state_=(new_character_state: Boolean): Unit = {
    _state = new_character_state
  }

  /**
   * Performs an attack on another entityE.
   *
   * @param entity The entityE being attacked.
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
   */
  def doAttack(entity: Entity): Unit = {}

  /**
   * Indicates whether the entityE is controlled by the player or not.
   *
   * @return true if the entityE is controlled by the player, false otherwise.
   */
  def isPlayer: Boolean = _isPlayer

  /**
   * Performs healing on another entityE.
   *
   * @param entity The entityE being healed.
   * @param heal The amount of healing to be performed.
   */
  def doHealing(entity: Entity, heal: Int): Unit = {
    doHeal(entity: Entity, heal: Int)
  }

  /**
   * Logic of healing on another entityE.
   *
   * @param entity The entityE performing the healing.
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
   * Checks if the target entityE is valid for an offensive spell.
   *
   * @param spell The offensive spell being cast.
   */
  def checkSpell(spell: OffensiveSpell): Unit = {
    throw new InvalidSpellTarget(s"${spell.name} is a offensive spell, can not be cast on ${this.name}")
  }
  /**
   * Checks if the target entityE is valid for a defensive spell.
   *
   * @param spell The defensive spell being cast.
   */
  def checkSpell(spell: DefensiveSpell): Unit = {
    throw new InvalidSpellTarget(s"${spell.name} is a defensive spell, can not be cast on ${this.name}")
  }

  /**
   * Gets the active effects Set
   *
   * @return effects Set.
   */
  def activeEffects: ListBuffer[Effect] = _activeEffects

  /**
   * Add effect to current active effects.
   *
   * @param effect New added effect.
   */
  def addEffect(effect: Effect): Unit = {
    if (_activeEffects.exists(_.effectName == effect.effectName)) {
      throw new RepeatedEffect(s"Effect ${effect.effectName} is already active and cannot be added again.")
    } else {
      _activeEffects += effect
    }
  }

  def updateEffects(): Unit = {
    _activeEffects.foreach { effect =>
      effect.applyEffect(this)
    }
    _activeEffects.filterInPlace(_.turnEffect > 0)
  }

  def skipTurn: Boolean = _skipTurn

  def skipTurn_=(bool: Boolean): Unit = {
    _skipTurn = bool
  }

}
