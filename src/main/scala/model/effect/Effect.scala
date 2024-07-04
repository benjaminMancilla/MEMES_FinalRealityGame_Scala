package model.effect

import model.entity.Entity

trait Effect {
  def passTurn() : Unit
  def applyEffect(target: Entity): Unit
  def turnEffect: Int
  def turnEffect_=(newTurnEffect: Int): Unit
  def effectName: String


}
