package entity.enemy

import entity.character.Character
import entity.{AbstractEntity, Entity}
import exceptions.{InvalidSpellTarget, ProhibitedTarget, Require}
import magic.OffensiveSpell

/**
 * Concrete class representing an enemy entity in a game.
 * An enemy is a character that can attack.
 *
 * @param nameI       The name of the enemy.
 * @param hit_pointsI The initial hit points of the enemy.
 * @param defenseI    The defense level of the enemy.
 * @param weightI     The weight of the enemy.
 * @param attackI     The attack strength of the enemy.
 */
class ConcreteEnemy(nameI: String, hit_pointsI: Int, defenseI: Int, weightI: Int, attackI: Int)
  extends AbstractEntity(nameI, hit_pointsI, defenseI, weightI) with Enemy {

  /**
   * Attack strength of the enemy.
   */
  private val _attack: Int = {Require.Stat(attackI, "attack") in (0 to 10000)}


  /**
   * Indicates whether the enemy is controlled by the player.
   */
  override val _isPlayer = false

  /**
   * Gets the attack strength of the enemy.
   *
   * @return The attack strength of the enemy.
   */
  def attack: Int = _attack

  /**
   * Value for the enemy's status bar (e.g., representing visually the weight).
   *
   * @return The value for the enemy's status bar.
   */
  def barValue: Int = {
    weight
  }


  /**
   * An enemy can not perform an attack on other enemy.
   *
   * @return ProhibitedTarget exception.
   */
  def doAttack(entity: ConcreteEnemy, damage: Int): Unit = {
    throw new ProhibitedTarget("An enemy can not attack other enemies")
  }

  /**
   * Same logic than the general entity attack
   *
   * @return Unit
   */
  def doAttack(entity: Character, damage: Int): Unit = {
    doDamage(entity: Entity, damage: Int)
  }

  /**
   * Enemies can only receive offensive spells
   *
   * @return Unit
   */
  override def checkSpell(spell: OffensiveSpell): Unit = {}

}

