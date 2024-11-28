package model.effect

/**
 * Trait representing a state effect that can be applied to an entity in a game.
 *
 * State effects are special conditions that affect an entity's capabilities, such as
 * being paralyzed, unable to change weapons, or other status changes that alter the
 * entity's behavior or actions. This trait extends the basic `Effect` trait to represent
 * these specific types of effects.
 */
trait StateEffect extends Effect
