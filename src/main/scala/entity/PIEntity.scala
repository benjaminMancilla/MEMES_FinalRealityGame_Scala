package entity

trait PIEntity {

  def name: String
  def hit_points: Int
  def defense: Int
  def weight: Int
  def current_hit_points: Int
  def state: Boolean
  def barValue: Int
  def isPlayer: Boolean

}
