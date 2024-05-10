package entity


trait Entity extends PIEntity {

  def current_hit_points_=(new_current_hit_points: Int): Unit
  def state_=(new_character_state: Boolean): Unit
  def doDamage(entity: Entity, damage: Int): Unit
  def receiveDamage(damage: Int): Int
  def doAttack(entity: Entity, damage: Int): Unit



}
