package controller.visitor

import entity.character.Character
import entity.character.magicCharacter.MagicCharacter
import entity.enemy.Enemy

class GeneralActionVisitor extends ActionVisitor {
  def visitEnemy(enemy: Enemy): List[String] = {List[String]("Attack")}
  def visitRegularCharacter(character: Character): List[String] = {List[String]("Attack", "Weapon", "Pass")}
  def visitMagicCharacter(character: MagicCharacter): List[String] = {List[String]("Attack", "Weapon", "Pass", "Magic")}

  //Lo que esta puesto no, IDEA: visitor maneja el input, dependiendo de la entidad es lo que pregunta y
  // las opciones que da(en caso de ser enemy genera una respuesta aleatoria y predeterminada). Una vez recibida
  //una respuesta valida, se la entrega al action state, este tiene los condicionales para las 4 opciones totales
  // (magic, attack, weapon, pass) y hace un match para guardar el state que corresponde a la respuesta (lo guarda)
  // luego en update hacemos changeState a ese estado guarado. Esta idea aplica igual a los demas Actionstates, solo
  // cambian las respuestas correctas.

}
