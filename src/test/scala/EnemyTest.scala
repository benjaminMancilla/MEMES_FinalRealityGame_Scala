import munit.FunSuite
import enemy.ConcreteEnemy
class EnemyTest extends FunSuite {

  test("ConcreteEnemy should initialize correctly") {
    val enemy = new ConcreteEnemy("Orc", 50, 10, 50, 20)
    assert(enemy.name == "Orc")
    assert(enemy.hitPoints == 50)
    assert(enemy.defense == 10)
    assert(enemy.weight == 50)
    assert(enemy.attack == 20)
    assert(enemy.currentHitPoints == 50)
    assert(enemy.enemyState == true)
  }

  test("ConcreteEnemy should update currentHitPoints correctly") {
    val enemy = new ConcreteEnemy("Goblin", 30, 5, 40, 15)
    enemy.currentHitPoints -= 10
    assert(enemy.currentHitPoints == 20)
  }

  test("ConcreteEnemy should update enemyState correctly") {
    val enemy = new ConcreteEnemy("Troll", 80, 15, 60, 25)
    enemy.enemyState = false
    assert(enemy.enemyState == false)
  }

}
