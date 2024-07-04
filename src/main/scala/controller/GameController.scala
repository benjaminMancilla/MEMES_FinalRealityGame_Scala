package controller
import controller.state.GameState
import model.entity.Entity
import model.turn.TurnScheduler
import model.weapon.Weapon

import scala.collection.mutable.ArrayBuffer

trait GameController {
  def setState(state: GameState): Unit

  def handleInput(input: String): Unit

  def update(): Unit

  def actionBarIncrease : Int

  def weaponInventory: List[Weapon]

  def turnCurrentSate: ArrayBuffer[(Entity, Int, Int, Boolean)]

  def turnScheduler: TurnScheduler

  def currentState: GameState

  def addWeapon(weapon: Weapon): Unit

  def combatResult: String

  def combatResult_=(string: String): Unit

}
