package vista

import controller.GameController
import model.magic.Magic
import model.weapon.Weapon

class GameView(controller: GameController) {
  var displayLog: List[String] = List.empty

  def displayGameState(): Unit = {
    val output = new StringBuilder
    output.append("----- GAME STATE -----\n")
    for ((entity, actionBarValue, _, state) <- controller.turnCurrentSate) {
      output.append(s"Entity: ${entity.name}, HP: ${entity.current_hit_points}, Action Bar: $actionBarValue, State: $state\n")
    }
    output.append("-----------------------------\n")
    displayLog = displayLog :+ output.toString
    println(output.toString)
  }

  def displayTurnScheduler(): Unit = {
    val output = s"Ready Entities\n${controller.turnScheduler.readyEntities().map(_.name).mkString(", ")}"
    displayLog = displayLog :+ output
    println(output)
  }

  def displayNextAttacker(): Unit = {
    val output = try {
      s"Next Attacker: ${controller.turnScheduler.nextAttacker.name}"
    } catch {
      case _: IndexOutOfBoundsException => "No more ready entities"
    }
    displayLog = displayLog :+ output
    println(output)
  }

  def displayCombatResult(): Unit = {
    val output = s"Combat result: ${controller.combatResult}"
    displayLog = displayLog :+ output
    println(output)
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
    val output = new StringBuilder
    output.append("Select a spell to cast:\n")
    appendOptions(output, spells)
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  def displayOptionCommon(options: List[String]): Unit = {
    val output = new StringBuilder
    output.append(s"${controller.turnScheduler.nextAttacker.name}\nSelect an action:\n")
    appendOptions(output, options)
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  def displayOptionWeapon(weapons: List[Weapon]): Unit = {
    val output = new StringBuilder
    output.append("Select a weapon:\n")
    appendOptions(output, weapons.map(_.name))
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  def displayOptionTarget(targets: List[String]): Unit = {
    val output = new StringBuilder
    output.append("Select a target:\n")
    appendOptions(output, targets)
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  private def appendOptions(output: StringBuilder, options: List[String]): Unit = {
    options.zipWithIndex.foreach { case (option, index) =>
      output.append(s"${index + 1}. $option\n")
    }
  }
}



