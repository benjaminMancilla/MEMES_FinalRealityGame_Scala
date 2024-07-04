package controller.state

import controller.GameController
import controller.visitor.GameStateVisitor

trait GameState {
  def handleInput(input: String): Unit
  def update(): Unit
  def needInput(): Boolean
  def getController: GameController
  def accept(visitor: GameStateVisitor): Unit
}

