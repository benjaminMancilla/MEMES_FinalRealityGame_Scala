package entity

abstract class AbstractEntity(nameI: String, hit_pointsI: Int, defenseI: Int, weightI:Int) extends Entity with PIEntity {


  private val _name: String = try {
    if (nameI.nonEmpty) nameI else throw new IllegalArgumentException("Name can not be Empty.")
  } catch {
    case _: IllegalArgumentException => "Entity"
  }

  private val _hit_points: Int = try {
    if (hit_pointsI>0) hit_pointsI else throw new IllegalArgumentException("Hit points must be larger than 0")
  } catch {
    case _: IllegalArgumentException => 1
  }


  private val _defense: Int = try {
    if (defenseI>=0) defenseI else throw new IllegalArgumentException("Defense must be larger than -1")
  } catch {
    case _: IllegalArgumentException => 0
  }

  private val _weight: Int = try {
    if (weightI>=0) weightI else throw new IllegalArgumentException("Weight must be larger than -1")
  } catch {
    case _: IllegalArgumentException => 0
  }

  protected val _isPlayer: Boolean


  private var _current_hit_points: Int = _hit_points
  private var _state: Boolean = true


  def name: String = _name
  def hit_points: Int = _hit_points
  def defense: Int = _defense
  def weight: Int = _weight
  def current_hit_points: Int = _current_hit_points
  def current_hit_points_=(new_current_hit_points: Int): Unit = {
    _current_hit_points = new_current_hit_points
  }
  def state: Boolean = _state
  def state_=(new_character_state: Boolean): Unit = {
    _state = new_character_state
  }

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

  def doAttack(entity: Entity, damage: Int): Unit = {
    doDamage(entity: Entity, damage: Int)
  }

  def isPlayer: Boolean = _isPlayer




}
