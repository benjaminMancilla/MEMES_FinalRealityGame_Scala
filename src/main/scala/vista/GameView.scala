package vista

import controller.GameController
import model.magic.Magic
import model.weapon.Weapon

class GameView(controller: GameController) {

  private def displayGameState(): Unit = {
    println("----- GAME STATE -----")
    for ((entity, actionBarValue, _, state) <- controller.turnCurrentSate) {
      println(s"Entity: ${entity.name}, HP: ${entity.current_hit_points}, Barra de Acción: $actionBarValue, Estado: $state")
    }
    println("-----------------------------")
  }

  private def displayTurnScheduler(): Unit = {
    println("Ready Entities")
    println(controller.turnScheduler.readyEntities().map(_.name).mkString(", "))
  }

  private def displayNextAttacker(): Unit = {
    try {
      println(s"Next Attacker: ${controller.turnScheduler.nextAttacker.name}")
    } catch {
      case _: IndexOutOfBoundsException =>
        println("No more ready entities")
    }
  }

  def displayCombatResult(): Unit = {
    println(s"Combat result: ${controller.combatResult}")
  }

  def promptPlayerInput(): String = {
    println("Enter your action:")
    val command = scala.io.StdIn.readLine()
    command match {
      case "Info" =>
        displayNextAttacker()
        "Invalid"

      case "Ready" =>
        displayTurnScheduler()
        "Invalid"

      case "Game" =>
        displayGameState()
        "Invalid"

      case _ =>
        command
    }
  }

  def displayOptionSpell(spells: List[String]): Unit = {
    println("Select a spell to cast:")
    printOptions(spells)
  }

  def displayOptionCommon(options: List[String]): Unit = {
    println(controller.turnScheduler.nextAttacker.name)
    println("Select an action:")
    printOptions(options)
  }

  def displayOptionWeapon(weapons: List[Weapon]): Unit = {
    println("Select a weapon:")
    printOptions(weapons.map(_.name))
  }

  def displayOptionTarget(targets: List[String]): Unit = {
    println("Select a target:")
    printOptions(targets)
  }


  private def printOptions(options: List[String]): Unit = {
    options.zipWithIndex.foreach { case (option, index) =>
      println(s"${index + 1}. $option")
    }
  }
}
