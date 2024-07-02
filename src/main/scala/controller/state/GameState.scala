package controller.state

trait GameState {
  def handleInput(input: String): Unit
  def update(): Unit
  def needInput(): Boolean
}

