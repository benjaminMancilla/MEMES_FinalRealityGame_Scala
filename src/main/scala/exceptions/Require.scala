package exceptions

import exceptions.weaponE.InvalidWeaponException
import weapon.Weapon
import entity.character.Character
import exceptions.stateE.InvalidWeaponInventory
import party.Party


/** The `Require` object provides utilities for validating specific constraints or requirements.
 *
 * This object includes the `Stat` class, designed to validate a statistic's value against
 * constraints like a specified range or a minimum threshold.
 *
 * ### Adding New Requirements:
 *
 * 1. Within the `Require` object, create a case class representing the requirement.
 *    While the `final case` modifier is optional, it's recommended for better control over class
 *    hierarchy.
 * 2. Implement a validation method in the new case class. This method should either return the
 *    valid value or raise an exception for invalid cases.
 */
object Require {

  /**
   * Represents and validates a game statistic with an associated name.
   *
   * This class facilitates the validation of a statistic's value against specified constraints.
   * An [[InvalidStatException]] is thrown if the validation fails.
   *
   * Note:
   * The class is marked final and cannot be extended.
   *
   * @param value The numeric value of the statistic.
   * @param name The identifier or label for the statistic, primarily used in error messages.
   */
  final case class Stat(value: Int, name: String) {

    /**
     * Validates if the statistic's value lies within a provided range.
     *
     * @example
     * {{{
     *   val health = Require.Stat(100, "health") in (0 to 100)
     *   // => health: Int = 100
     * }}}
     * @example
     * {{{
     *   val health = Require.Stat(200, "health") in (0 to 100)
     *   // => throws InvalidStatException
     * }}}
     *
     * @param range The permissible range for the statistic's value.
     * @return The statistic's value if within the range.
     * @throws InvalidStatException for values outside the range.
     */
    def in(range: Range): Int =
      if (range contains value) value
      else throw new InvalidStatException(s"$name should be in $range but was $value")

    /**
     * Validates if the statistic's value is greater than or equal to a specified limit.
     *
     * @example
     * {{{
     *   val health = Require.Stat(100, "health") atLeast 0
     *   // => health: Int = 100
     * }}}
     * @example
     * {{{
     *   val health = Require.Stat(-10, "health") atLeast 0
     *   // => throws InvalidStatException
     * }}}
     *
     * @param lo The minimum accepted value for the statistic.
     * @return The statistic's value if it meets or exceeds the threshold.
     * @throws InvalidStatException for values below the threshold.
     */
    def atLeast(lo: Int): Int =
      if (value >= lo) value
      else throw new InvalidStatException(s"$name should be at least $lo but was $value")
  }

  /**
   * Represents and validates a name with specific constraints.
   *
   * This class facilitates the validation of a name's length against specified constraints.
   * An [[InvalidNameException]] is thrown if the validation fails.
   *
   * Note:
   * The class is marked final and cannot be extended.
   *
   * @param name The name to be validated.
   */
  final case class Name(name: String) {

    /**
     * Validates if the name's length is greater than or equal to a specified limit.
     *
     * @example
     * {{{
     *   val entityName = Require.Name("Dragon") lengthAtLeast 3
     *   // => entityName: String = "Dragon"
     * }}}
     * @example
     * {{{
     *   val entityName = Require.Name("Hi") lengthAtLeast 3
     *   // => throws InvalidNameException
     * }}}
     *
     * @param length The minimum length accepted for the name.
     * @return The name if it meets or exceeds the length threshold.
     * @throws InvalidNameException for names shorter than the threshold.
     */
    def lengthAtLeast(length: Int): String =
      if (name.length >= length) name
      else throw new InvalidNameException(s"The name of the entityE should be at least $length characters long.")
  }

  /**
   * Represents and validates the assignment of a weaponE to a character.
   *
   * This class facilitates the validation of a weaponE assignment against the character's
   * compatibility and ownership constraints. An [[InvalidWeaponException]] is thrown if
   * the validation fails.
   *
   * Note:
   * The class is marked final and cannot be extended.
   *
   * @param weapon The weaponE to be assigned, wrapped in an Option.
   * @param character The character to which the weaponE is to be assigned.
   */
  final case class WeaponAssignment(weapon: Option[Weapon], character: Character) {

    /**
     * Validates if the weaponE can be assigned to the character.
     *
     * @example
     * {{{
     *   val sword = Some(Weapon("Excalibur", None))
     *   val knight = Character("Arthur", Set("sword"))
     *   val assignment = Require.WeaponAssignment(sword, knight).validWeapon(sword, knight)
     *   // => assignment: Option[Weapon] = Some(Weapon("Excalibur", Some(knight)))
     * }}}
     * @example
     * {{{
     *   val axe = Some(Weapon("Battle Axe", None))
     *   val wizard = Character("Merlin", Set("staff"))
     *   val assignment = Require.WeaponAssignment(axe, wizard).validWeapon(axe, wizard)
     *   // => throws InvalidWeaponException
     * }}}
     *
     * @param weaponOpt The optional weaponE to be validated.
     * @param character The character to which the weaponE is to be assigned.
     * @return The weaponE wrapped in an Option if valid for the character.
     * @throws InvalidWeaponException for invalid weaponE assignments.
     */
    def validWeapon(weaponOpt: Option[Weapon], character: Character): Option[Weapon] = {
      weaponOpt.flatMap { weapon =>
        if (!character.checkValidWeapon(Some(weapon))) {
          throw new InvalidWeaponException(s"Cannot equip ${weapon.name} on this character")
        } else {
          weapon.owner.map { owner =>
            val ownerName = owner.name
            throw new InvalidWeaponException(s"${weapon.name} is already being used by $ownerName")
          }.orElse(Some(weapon))
        }
      }
    }
  }

  final case class WeaponInventory(inventory: List[Weapon], party: Party, name: String) {
    private val partySize: Int = party.characters.size
    private val weaponSize: Int = inventory.size
    def sizeAtLeast(): List[Weapon] = {
      if (partySize>weaponSize){
        throw new InvalidWeaponInventory(s"Not enough weapons for the party, at least 1 per character")
      }
      inventory

    }

  }

}
