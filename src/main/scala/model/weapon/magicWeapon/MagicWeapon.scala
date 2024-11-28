package model.weapon.magicWeapon

import model.weapon.Weapon

/**
 * Trait representing a specific magical weaponE.
 * Magical weapons are a subtype of weapons and possess additional magical attack capabilities.
 * They inherit properties and methods from both the `Weapon` and `PIMagicWeapon` traits.
 */
trait MagicWeapon extends Weapon with PIMagicWeapon

