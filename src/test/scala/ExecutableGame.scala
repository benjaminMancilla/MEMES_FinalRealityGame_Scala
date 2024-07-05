import controller.GameControllerConcrete
import controller.visitor.StateOptionVisitor
import model.entity.Entity
import model.entity.character.Character
import model.entity.character.commonCharacter.Warrior
import model.entity.character.magicCharacter.BlackMage
import model.entity.enemy.ConcreteEnemy
import model.party.ConcreteParty
import model.turn.TurnScheduler
import model.weapon.Weapon
import model.weapon.commonWeapon.Axe
import model.weapon.magicWeapon.Staff
import vista.GameView

import scala.collection.mutable.ArrayBuffer

object ExecutableGame {

  def main(args: Array[String]): Unit = {
    val partyBuffer: ArrayBuffer[Character] = ArrayBuffer(
      new Warrior("Conan", 200, 30, 50),
      new Warrior("Conan2", 100, 20, 20),
      new BlackMage("Mage", 200, 30, 40, 100)
    )
    val turnBuffer: ArrayBuffer[Entity] = ArrayBuffer(
      new Warrior("Conan", 200, 30, 40),
      new Warrior("Conan2", 200, 30, 40),
      new BlackMage("Mage", 200, 30, 40, 100),
      new ConcreteEnemy("Goblin1", 100, 5, 100, 30)
    )
    val party = new ConcreteParty(partyBuffer)
    val turnScheduler = new TurnScheduler(turnBuffer)
    val actionBarIncreaseI = 10
    val inventoryI: List[Weapon] = List(new Axe("Battle Axe1", 10, 20), new Axe("Battle Axe2", 10, 20), new Staff("Magic Staff", 10, 15, 50))
    val controller = new GameControllerConcrete(party, turnScheduler, actionBarIncreaseI, inventoryI)
    val view = new GameView(controller)
    val visitor = new StateOptionVisitor(view)

    while (controller.combatResult != "Victory" && controller.combatResult != "Defeat") {
      //for ((entity, actionBarValue, otherValue, state) <- controller.turnScheduler.turn_info) {
      //  println(s"Entity: ${entity.name}, Action Bar: $actionBarValue, Other Value: $otherValue, State: $state")
      //}
      if (controller.currentState.needInput()) {
        controller.currentState.accept(visitor)
        val input = view.promptPlayerInput()
        controller.handleInput(input)
      }
      controller.update()
    }
    view.displayCombatResult()
  }
}

