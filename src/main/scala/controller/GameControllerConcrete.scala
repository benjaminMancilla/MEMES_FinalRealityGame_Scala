package controller

import controller.state.{GameState, StartState}
import exceptions.Require
import turn.TurnScheduler
class GameControllerConcrete(turnScheduler: TurnScheduler, actionBarIncreaseI: Int)  extends GameController{

  private var currentState: GameState = new StartState(this, turnScheduler)
  private val _actionBarIncrease = {Require.Stat(actionBarIncreaseI, "actionBarIncrease") atLeast 1 }

  def setState(state: GameState): Unit = {
    currentState = state
  }

  def handleInput(input: String): Unit = {
    currentState.handleInput(input)
  }

  def update(): Unit = {
    currentState.update()
  }

  def actionBarIncrease: Int = _actionBarIncrease




}
