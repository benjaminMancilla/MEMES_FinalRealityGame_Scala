import entity.enemy.ConcreteEnemy
import munit.FunSuite
class EnemyTest extends FunSuite {

  test("ConcreteEnemy should not initialize with invalid parameters"){
    val enemy = new ConcreteEnemy("Orc", 50, 10, 50, -20)
    assert(enemy.attack == 0)
  }

  test("ConcreteEnemy should initialize correctly") {
    val enemy = new ConcreteEnemy("Orc", 50, 10, 50, 20)
    assert(enemy.name == "Orc")
    assert(enemy.hit_points == 50)
    assert(enemy.defense == 10)
    assert(enemy.weight == 50)
    assert(enemy.attack == 20)
    assert(enemy.current_hit_points == 50)
    assert(enemy.state)
  }

  test("ConcreteEnemy should update currentHitPoints correctly") {
    val enemy = new ConcreteEnemy("Goblin", 30, 5, 40, 15)
    enemy.current_hit_points -= 10
    assert(enemy.current_hit_points == 20)
  }

  test("ConcreteEnemy should update enemyState correctly") {
    val enemy = new ConcreteEnemy("Troll", 80, 15, 60, 25)
    enemy.state = false
    assert(!enemy.state)
  }

}
