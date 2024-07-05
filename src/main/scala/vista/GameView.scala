package vista

import controller.GameController
import model.weapon.Weapon

/**
 * Class representing the game view responsible for displaying game state and managing user interaction.
 *
 * @param controller The game controller that manages game logic and state.
 */
class GameView(controller: GameController) {

  /**
   * List to store the log of displayed messages.
   */
  var displayLog: List[String] = List.empty

  /**
   * Displays the current game state including entity information, action bar values, and states.
   */
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

  /**
   * Displays the list of entities that are ready to perform actions in the turn scheduler.
   */
  def displayTurnScheduler(): Unit = {
    val output = s"Ready Entities\n${controller.turnScheduler.readyEntities().map(_.name).mkString(", ")}"
    displayLog = displayLog :+ output
    println(output)
  }

  /**
   * Displays the name of the next entity scheduled to perform an action.
   * Displays an error message if there are no more entities ready.
   * In this case, the nextAttacker correspond to the current entity
   * playing it's turn.
   */
  def displayNextAttacker(): Unit = {
    val output = try {
      s"Next Attacker: ${controller.turnScheduler.nextAttacker.name}"
    } catch {
      case _: IndexOutOfBoundsException => "No more ready entities"
    }
    displayLog = displayLog :+ output
    println(output)
  }

  /**
   * Displays the result of the combat phase.
   */
  def displayCombatResult(): Unit = {
    val output = s"Combat result: ${controller.combatResult}"
    displayLog = displayLog :+ output
    println(output)
  }

  /**
   * Prompts the player for input and returns the entered command.
   * Displays options for viewing next attacker, turn scheduler, and game state.
   *
   * @return The player's input command or "Invalid" for predefined commands.
   */
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

  /**
   * Displays a list of spells the player can choose to cast.
   *
   * @param spells List of spells available to the player.
   */
  def displayOptionSpell(spells: List[String]): Unit = {
    val output = new StringBuilder
    output.append("Select a spell to cast:\n")
    appendOptions(output, spells)
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  /**
   * Displays a list of common actions the player can choose for the next attacker.
   *
   * @param options List of common actions available to the player.
   */
  def displayOptionCommon(options: List[String]): Unit = {
    val output = new StringBuilder
    output.append(s"${controller.turnScheduler.nextAttacker.name}\nSelect an action:\n")
    appendOptions(output, options)
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  /**
   * Displays a list of weapons the player can choose for the next attacker.
   *
   * @param weapons List of weapons available to the player.
   */
  def displayOptionWeapon(weapons: List[Weapon]): Unit = {
    val output = new StringBuilder
    output.append("Select a weapon:\n")
    appendOptions(output, weapons.map(_.name))
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  /**
   * Displays a list of targets the player can choose for the next attacker.
   *
   * @param targets List of target names available to the player.
   */
  def displayOptionTarget(targets: List[String]): Unit = {
    val output = new StringBuilder
    output.append("Select a target:\n")
    appendOptions(output, targets)
    displayLog = displayLog :+ output.toString.trim
    println(output.toString.trim)
  }

  /**
   * Helper method to append indexed options to a StringBuilder.
   *
   * @param output  The StringBuilder to append options to.
   * @param options List of options to display.
   */
  private def appendOptions(output: StringBuilder, options: List[String]): Unit = {
    options.zipWithIndex.foreach { case (option, index) =>
      output.append(s"${index + 1}. $option\n")
    }
  }
}



