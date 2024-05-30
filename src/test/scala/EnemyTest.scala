import entity.character.commonCharacter.Warrior
import entity.enemy.ConcreteEnemy
import exceptions.InvalidStatException
import exceptions.entity.ProhibitedTarget
import munit.FunSuite
class EnemyTest extends FunSuite {

  test("ConcreteEnemy should not initialize with invalid parameters"){
    intercept[InvalidStatException]{
      new ConcreteEnemy("Orc", 50, 10, 50, -20)
    }
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

  test("An enemy can not attack another enemy"){
    val enemy = new ConcreteEnemy("Troll", 80, 15, 60, 25)
    val enemy2 = new ConcreteEnemy("Goblin", 30, 5, 40, 15)
    intercept[ProhibitedTarget]{
      enemy.doAttack(enemy2, 10)
    }
  }

  test("An enemy should be able to attack a Character"){
    val enemy = new ConcreteEnemy("Troll", 80, 15, 60, 25)
    val warrior = new Warrior("Juan", 100, 10, 10)
    enemy.doAttack(warrior, 20)
    assert(warrior.current_hit_points == 90)
  }

}
