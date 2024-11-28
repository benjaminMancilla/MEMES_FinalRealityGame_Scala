## Final Reality

Final Reality is a simplified clone inspired by the classic game, Final Fantasy, designed primarily as an educational tool to teach fundamental programming concepts.

This project is licensed under the [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/).

### Task 2 | README

#### Design

In this project, several design decisions were made to enhance clarity, maintainability, and expandability of the codebase:

- **Option[Weapon] for Equipped Weapons:** Replaced the EmptyWeapon class with Option[Weapon] to simplify weapon management and eliminate null states. This change improved the handling of weapon equipping and unequipping operations.

- **Double Dispatch for Entity Actions:** Implemented double dispatch and method overloading to differentiate actions between enemies and characters. This approach ensures that attacks and spells are appropriately directed based on the type of entity.

- **Custom Exceptions:** Introduced custom exceptions with descriptive error messages to handle edge cases effectively. These exceptions provide clear feedback during runtime and aid in debugging and error resolution.

- **Magic Handling with Double Dispatch:** Extended the use of double dispatch for magic handling, ensuring that spells are cast only on valid targets and by appropriate spellcasters (e.g., BlackMage, WhiteMage).

- **Package Organization:** Organized code into packages based on functionality (e.g., exceptions, magic, weapons, characters) to maintain a modular and structured codebase. This organization facilitates easier navigation and enhances code readability.

#### State Diagram

![State Diagram](docs/Diagrama%20MEMES3.jpg)

#### Design Patterns

The project utilizes several design patterns to manage game states, actions, and interactions:

- **State Pattern:** Implemented using various state classes (`StartState`, `UpdateBarState`, `SelectTargetState`, etc.) to represent different phases of gameplay and manage transitions between them.

- **Visitor Pattern:** Applied to handle actions and behaviors based on entity types (e.g., `ActionVisitor`, `SelectSpellVisitor`). This pattern allows flexible handling of different entity interactions without modifying the entities themselves.

- **Command Pattern:** Utilized for game actions (`GameCommand` subclasses like `AttackGameCommand`, `SpellGameCommand`, etc.) to encapsulate specific operations and allow their execution at different points in the game flow.

These patterns collectively contribute to a flexible, extensible, and maintainable game architecture, supporting future enhancements and modifications.

