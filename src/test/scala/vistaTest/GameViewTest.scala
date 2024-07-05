package vistaTest

import controllerTest.ControllerGenerator
import model.weapon.commonWeapon.Axe
import model.weapon.magicWeapon.Staff
import vista.GameView

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}
import munit.FunSuite

class GameViewTest extends ControllerGenerator {

  def captureOutput(f: => Unit): String = {
    val outCapture = new ByteArrayOutputStream
    Console.withOut(new PrintStream(outCapture)) {
      f
    }
    outCapture.toString
  }

  test("GameView should display game state correctly") {
    val view = new GameView(controller)
    val output = captureOutput {
      view.displayGameState()
    }
    val expectedOutput =
      """----- GAME STATE -----
        |Entity: Heman, HP: 100, Action Bar: 0, State: false
        |Entity: Saruman, HP: 60, Action Bar: 0, State: false
        |Entity: Garu, HP: 80, Action Bar: 0, State: false
        |Entity: Goblin1, HP: 30, Action Bar: 0, State: false
        |Entity: Goblin2, HP: 30, Action Bar: 0, State: false
        |Entity: Goblin3, HP: 30, Action Bar: 0, State: false
        |-----------------------------
        |""".stripMargin
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should display ready entities correctly") {
    val view = new GameView(controller)
    val output = captureOutput {
      view.displayTurnScheduler()
    }
    val expectedOutput = "Ready Entities\n"
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should display next attacker correctly") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    val view = new GameView(controller)
    val output = captureOutput {
      view.displayNextAttacker()
    }
    val expectedOutput = "Next Attacker: Goblin3\n"
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should display combat result correctly") {
    val view = new GameView(controller)
    controller.combatResult = "Victory"
    val output = captureOutput {
      view.displayCombatResult()
    }
    val expectedOutput = "Combat result: Victory\n"
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should display spell options correctly") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val view = new GameView(controller)
    val spells = List("Fire", "Thunder")
    val output = captureOutput {
      view.displayOptionSpell(spells)
    }
    val expectedOutput =
      """Select a spell to cast:
        |1. Fire
        |2. Thunder
        |""".stripMargin
    assertNoDiff(output, expectedOutput)

    controller2.turnScheduler.updateActionProgress(1000)
    controller2.turnScheduler.checkWaitEntities()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    controller2.turnScheduler.dequeueReady()
    val view2 = new GameView(controller)
    val spells2 = List("Poison", "Paralyze", "Heal")
    val output2 = captureOutput {
      view.displayOptionSpell(spells2)
    }
    val expectedOutput2 =
      """Select a spell to cast:
        |1. Poison
        |2. Paralyze
        |3. Heal
        |""".stripMargin
    assertNoDiff(output2, expectedOutput2)
  }

  test("GameView should display common options correctly") {
    controller.turnScheduler.updateActionProgress(1000)
    controller.turnScheduler.checkWaitEntities()

    val view = new GameView(controller)
    val options = List()
    val output = captureOutput {
      view.displayOptionCommon(options)
    }
    val expectedOutput =
      s"""${controller.turnScheduler.nextAttacker.name}
         |Select an action:
         |""".stripMargin
    assertNoDiff(output, expectedOutput)

    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val options2 = List("Attack", "Magic", "Weapon", "Run")
    val output2 = captureOutput {
      view.displayOptionCommon(options2)
    }
    val expectedOutput2 =
      s"""${controller.turnScheduler.nextAttacker.name}
         |Select an action:
         |1. Attack
         |2. Magic
         |3. Weapon
         |4. Run
         |""".stripMargin
    assertNoDiff(output2, expectedOutput2)

    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()

    val options3 = List("Attack", "Weapon", "Run")
    val output3 = captureOutput {
      view.displayOptionCommon(options3)
    }
    val expectedOutput3 =
      s"""${controller.turnScheduler.nextAttacker.name}
         |Select an action:
         |1. Attack
         |2. Weapon
         |3. Run
         |""".stripMargin
    assertNoDiff(output3, expectedOutput3)
  }

  test("GameView should display weapon options correctly") {
    val view = new GameView(controller)
    val weapons = List(new Axe("Battle Axe", 60, 20), new Staff("Magic Staff", 70, 15, 50))
    val output = captureOutput {
      view.displayOptionWeapon(weapons)
    }
    val expectedOutput =
      """Select a weapon:
        |1. Battle Axe
        |2. Magic Staff
        |""".stripMargin
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should display target options correctly") {
    val view = new GameView(controller)
    val targets = List("Goblin1", "Goblin2", "Goblin3")
    val output = captureOutput {
      view.displayOptionTarget(targets)
    }
    val expectedOutput =
      """Select a target:
        |1. Goblin1
        |2. Goblin2
        |3. Goblin3
        |""".stripMargin
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should prompt player input correctly and handle Info command") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val view = new GameView(controller)
    val input = "Info\n"
    val inputStream = new ByteArrayInputStream(input.getBytes)
    val output = captureOutput {
      Console.withIn(inputStream) {
        assertEquals(view.promptPlayerInput(), "Invalid")
      }
    }
    val expectedOutput = "Enter your action:\nNext Attacker: Saruman\n"
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should prompt player input correctly and handle Ready command") {
    controller.turnScheduler.updateActionProgress(100)
    controller.turnScheduler.checkWaitEntities()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    controller.turnScheduler.dequeueReady()
    val view = new GameView(controller)
    val input = "Ready\n"
    val inputStream = new ByteArrayInputStream(input.getBytes)
    val output = captureOutput {
      Console.withIn(inputStream) {
        assertEquals(view.promptPlayerInput(), "Invalid")
      }
    }
    val expectedOutput = "Enter your action:\nReady Entities\nSaruman, Goblin1, Heman\n"
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should prompt player input correctly and handle Game command") {
    val view = new GameView(controller)
    val input = "Game\n"
    val inputStream = new ByteArrayInputStream(input.getBytes)
    val output = captureOutput {
      Console.withIn(inputStream) {
        assertEquals(view.promptPlayerInput(), "Invalid")
      }
    }
    val expectedOutput =
      """Enter your action:
        |----- GAME STATE -----
        |Entity: Heman, HP: 100, Action Bar: 0, State: false
        |Entity: Saruman, HP: 60, Action Bar: 0, State: false
        |Entity: Garu, HP: 80, Action Bar: 0, State: false
        |Entity: Goblin1, HP: 30, Action Bar: 0, State: false
        |Entity: Goblin2, HP: 30, Action Bar: 0, State: false
        |Entity: Goblin3, HP: 30, Action Bar: 0, State: false
        |-----------------------------
        |""".stripMargin
    assertNoDiff(output, expectedOutput)
  }

  test("GameView should prompt player input correctly and handle custom command") {
    val view = new GameView(controller)
    val input = "Attack\n"
    val inputStream = new ByteArrayInputStream(input.getBytes)
    val output = captureOutput {
      Console.withIn(inputStream) {
        assertEquals(view.promptPlayerInput(), "Attack")
      }
    }
    val expectedOutput = "Enter your action:\n"
    assertNoDiff(output, expectedOutput)
  }
}

