package controller
import controller.state.GameState

trait GameController {
  def setState(state: GameState): Unit

  def handleInput(input: String): Unit

  def update(): Unit

  def actionBarIncrease : Int

}
