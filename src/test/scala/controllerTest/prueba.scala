package controllerTest

import controller.GameControllerConcrete
import entity.Entity
import entity.character.Character
import entity.character.commonCharacter.Warrior
import entity.character.magicCharacter.BlackMage
import entity.enemy.ConcreteEnemy
import party.{ConcreteParty, Party}
import turn.TurnScheduler
import weapon.Weapon
import weapon.commonWeapon.Axe
import weapon.magicWeapon.Staff

import scala.collection.mutable.ArrayBuffer

object prueba {
  def main(args: Array[String]): Unit = {
    // Inicialización de los parámetros necesarios para el controlador
    val partyBuffer: ArrayBuffer[Character] = ArrayBuffer(new Warrior("Conan", 200, 30, 40),new Warrior("Conan2", 200, 30, 40), new BlackMage("Conan3", 200, 30, 40, 100))
    val turnBuffer: ArrayBuffer[Entity] = ArrayBuffer(new Warrior("Conan", 200, 30, 40),new Warrior("Conan2", 200, 30, 40), new BlackMage("Conan3", 200, 30, 40, 100)
    ,new ConcreteEnemy("Goblin1", 30, 5, 40, 15), new ConcreteEnemy("Goblin2", 30, 5, 40, 15), new ConcreteEnemy("Goblin3", 30, 5, 45, 15) )
    val party = new ConcreteParty(partyBuffer) // Asegúrate de inicializar correctamente
    val turnScheduler = new TurnScheduler(turnBuffer) // Asegúrate de inicializar correctamente
    val actionBarIncreaseI = 1 // Ejemplo de valor
    val inventoryI: List[Weapon] = List(new Axe("Battle Axe1", 60, 20), new Axe("Battle Axe2", 60, 20), new Staff("Magic Staff", 70, 15, 50)) // Ejemplo de inicialización

    // Inicializar el controlador con los parámetros
    val controller = new GameControllerConcrete(party, turnScheduler, actionBarIncreaseI, inventoryI)

    // Bucle para leer la entrada del usuario y actualizar el controlador
    while (true) {
      // Obtener entrada del usuario

      for ((entity, actionBarValue, otherValue, state) <- controller.turnCurrentSate) {
        println(s"Entity: ${entity.name}, Action Bar: $actionBarValue, Other Value: $otherValue, State: $state")
      }
      if (controller.currentState.needInput()) {
        val input = scala.io.StdIn.readLine()
        controller.handleInput(input)
      }
      controller.update()
      try {
        println(controller.turnScheduler.nextAttacker.name)
      } catch {
        case _: IndexOutOfBoundsException =>
        // No hacer nada
      }

    }
  }
}
