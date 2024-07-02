package controller.state

class AbstractState extends GameState {
  def needInput(): Boolean = false
  def update(): Unit = {}
  def handleInput(input: String): Unit = {}
}
