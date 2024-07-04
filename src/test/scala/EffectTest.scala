import exceptions.InvalidStatException
import exceptions.effectE.{InvalidTurnAmount, RepeatedEffect}
import model.effect.{Burned, Paralyzed, Poisoned}
import model.entity.enemy.ConcreteEnemy
import munit.FunSuite

class EffectTest extends FunSuite{

  test("Effect should initialize correctly") {
    val burned = new Burned(magicDamageI = 10)
    val para = new Paralyzed()
    val poisoned = new Poisoned(magicDamageI = 20)
    assert(burned.effectName == "Burn effect")
    assert(para.effectName == "Paralyze effect")
    assert(poisoned.effectName == "Poison effect")

    assert(burned.turnEffect == 3)
    assert(para.turnEffect == 1)
    assert(poisoned.turnEffect == 4)

    assert(burned.magicDamage == 10)
    assert(poisoned.magicDamage == 20)

  }

  test("Effect can not have 0 or negative turns"){
    intercept[InvalidStatException]{
      new Burned(-1, 2)
    }
    intercept[InvalidStatException]{
      new Paralyzed(-10)
    }
    intercept[InvalidStatException]{
      new Poisoned(0, 2)
    }
  }

  test("Effect can not have negative magic damage"){
    intercept[InvalidStatException]{
      new Burned(10, -2)
    }

    intercept[InvalidStatException]{
      new Poisoned(0, -10)
    }
  }


  test("passTurn should pass 1 turn, if there are 0 or minus turns, should throw error") {
    val burned = new Burned(magicDamageI = 10)
    val para = new Paralyzed()
    val poisoned = new Poisoned(magicDamageI = 20)
    burned.passTurn()
    para.passTurn()
    poisoned.passTurn()
    assert(burned.turnEffect == 2)
    assert(para.turnEffect == 0)
    assert(poisoned.turnEffect == 3)
    intercept[InvalidTurnAmount] {
      para.passTurn()
    }
  }

  test("Burned should apply the corresponding dmg for 3 turns"){
    val dummy = new ConcreteEnemy("Orc", 50, 10, 50, 20)
    val burned = new Burned(2, 30)
    burned.applyEffect(dummy)
    assert(dummy.current_hit_points == 45)
    assert(burned.turnEffect == 1)

  }

  test("Poisoned should apply the corresponding dmg for 4 turns"){
    val dummy = new ConcreteEnemy("Orc", 50, 10, 50, 20)
    val poison = new Poisoned(2, 33)
    poison.applyEffect(dummy)
    assert(dummy.current_hit_points == 49)
    assert(poison.turnEffect == 1)

  }

  test("Paralyzed should apply put the entity in the skip state"){
    val dummy = new ConcreteEnemy("Orc", 50, 10, 50, 20)
    val para = new Paralyzed()
    para.applyEffect(dummy)
    assert(dummy.skipTurn)
    assert(para.turnEffect == 0)


  }

  test("addEffect should add correctly the effect to the entity, except "){
    val dummy = new ConcreteEnemy("Orc", 50, 10, 50, 20)
    val para = new Paralyzed()
    val poison = new Poisoned(2, 33)
    val poison2 = new Poisoned(20, 9)
    val burned = new Burned(2, 30)
    assert(dummy.activeEffects.isEmpty)
    dummy.addEffect(para)
    assert(dummy.activeEffects.size==1)
    dummy.addEffect(poison)
    assert(dummy.activeEffects.size==2)
    dummy.addEffect(burned)
    assert(dummy.activeEffects.size==3)
    intercept[RepeatedEffect]{
      dummy.addEffect(burned)
    }
    intercept[RepeatedEffect]{
      dummy.addEffect(poison)
    }
    intercept[RepeatedEffect]{
      dummy.addEffect(para)
    }
    intercept[RepeatedEffect]{
      dummy.addEffect(poison2)
    }

  }

  test("updateEffects should apply all effects and remove 0 turns effects") {
    val dummy = new ConcreteEnemy("Orc", 50, 10, 50, 20)
    val para = new Paralyzed()
    val poison = new Poisoned(2, 33)
    val burned = new Burned(2, 30)
    dummy.addEffect(para)
    dummy.addEffect(poison)
    dummy.addEffect(burned)
    dummy.updateEffects()
    assert(dummy.activeEffects.size==2)
    assert(dummy.skipTurn)
    assert(dummy.current_hit_points == 44)
    dummy.updateEffects()
    assert(dummy.activeEffects.isEmpty)
    assert(dummy.current_hit_points == 38)


  }






}
