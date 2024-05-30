package weapon.magicWeapon

import weapon.Weapon

/**
 * Trait representing a specific magical weapon.
 * Magical weapons are a subtype of weapons and possess additional magical attack capabilities.
 * They inherit properties and methods from both the `Weapon` and `PIMagicWeapon` traits.
 */
trait MagicWeapon extends Weapon with PIMagicWeapon

