# Final Reality

Final Reality is a simplified clone of the renowned game, Final Fantasy. Its main purpose is to
serve as an educational tool, teaching foundational programming concepts.

This README is yours to complete it. Take this opportunity to describe your contributions, the
design decisions you've made, and any other information you deem necessary.

This project is licensed under the
[Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/).

# Tarea 2 | README

## Design

An important change I made was to remove the EmptyWeapon class that represented having nothing equipped. While this simplification helped in many ways, when dealing with exceptions, the structure became less object-oriented. Explicitly checking whether the weapon type was an EmptyWeapon required a boolean method that returned true for an empty weapon and false for others. While this was functional, the design was poor, akin to glitches in some old games. There might have been a bug allowing a character to attack without a weapon. Breaking this logic was as easy as making a mistake in any of these methods. Now that the equipped weapon is an Option[Weapon], this cannot happen.

For assignment, I simply implemented double dispatch and also used overloading, which is legal since among the different concrete classes of Character, there is no overlap (they are "parallel"). However, I didn't define the five methods (one for checking each type of character) as false in the abstract class. Overriding could lead to confusion, and different names would be needed to avoid this, complicating the implementation. So, I left it as is—it's more "copy-paste" but I believe it's better. If a new character class is added in the future, the developer would have to actively implement the method for all weapons, making them think twice about whether the new character should be able to equip a certain weapon.

Throughout the code, I use custom exceptions with specific error messages. This will be very useful later when programming the controller. The names of these exceptions are quite specific to understand which edge case they handle. In case of doubts, the documentation provides more details.

One major benefit of changing the equipped weapon of Character to an Option[Weapon] is that unequipping is now trivial. Simply setting the owner of the old weapon to None and the new weapon to None suffices. Previously, every time a weapon was unequipped, a new EmptyWeapon had to be created, which was somewhat inelegant for the method.

To prevent attacks between allies, I opted for a simple strategy using overloading. Entities have two attack methods, one for enemies and the other for characters. I override each of these methods in their respective classes so that the method targeting an entity of the same class throws an exception, while the other contains the necessary logic for an effective attack. It's important to note that there is a doAttack(entity) method in the abstract entity as well. In practice (and I say in practice because I've done all the necessary tests and it works well), this general method is never used as such. However, I left it in place because there may be entities that do not fit into these two categories (for example, an obstacle, object, etc.), where an attack should be valid on these strange entities as long as they are entities, of course.

For magic, I also added more double dispatch, mainly to check if the target was valid and if the spellcaster met the same type as the spell. The rest in the body of the methods (especially castSpell) is fairly straightforward.

For healing, I left it general in the abstract entity class. The only thing that accesses this is the heal spell, but I think hypothetically in the future, items, actions, events, or anything else could be added that can heal both a character and an enemy or even another entity. Hence, I decided to leave it there.

An important aspect is that I added the getter method magicPoints to all weapons (i.e., to the Weapon trait). The logic is that magic can be accessed from any weapon, but if this happens, it will result in an error (exception). Magic weapons override this, removing the error and adding functionality to the getter method. This works easily as a dispatch for magical things accessing the equippedWeapon.

Also, TEMPORARILY for the applySpell methods, instead of applying the effect itself, I put a print statement. This was done because I wanted to test if that line of code was reached (even if it did nothing). In the future, simply removing the print and replacing it with the real implementation of the effect will suffice.

## Packages

For exceptions, the logic is if the main subject of the exception is X, then it goes into package X. For more general exceptions, they are kept in the general exception package.

For magic, I kept general things in 'magic', and specific things for a type (white or black) in their respective packages. Being defensive or offensive is not exclusive to a type of magic, so it's in the general package.

The same goes for weapons—common ones (which are only concrete classes in my case; I didn't create a trait for non-magical weapons) go in their package, and magical ones do too.

This also applies to characters.

I want to mention that I didn't want to do these last two things, but it was deducted for having the code organized this way, so I'm obliged to do it D: In general, I use packages for general things, at least the magic part doesn't seem so far from the norm, so I consider it an extension. In cases where generalization is difficult, I create more specific packages.