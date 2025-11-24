/**
 * Represents enemy characters in the game (like the Minotaur).
 * Extends Character to inherit common stats and behaviors.
 * Can be expanded with enemy-specific behaviors and AI logic.
 */
public class Enemy extends Character {
    
    /**
     * Constructor to create an enemy with specified combat statistics.
     * Passes all parameters to the parent Character constructor.
     * @param maxHitPoints Maximum and starting health
     * @param attackPoints Damage dealing capability
     * @param defense Damage reduction capability
     * @param speed Turn order/movement speed
     */
    public Enemy(int maxHitPoints, int attackPoints, int defense) {
        super(maxHitPoints, attackPoints, defense);
        // TODO Auto-generated constructor stub
        // This is where enemy-specific initialization would go
        // Examples: AI behavior patterns, loot tables, experience value
    }
    
    // Future enemy-specific methods could include:
    // - AI decision making (chooseAction)
    // - Special attack patterns
    // - Loot dropping on defeat
    // - Experience point value
    // - Enemy-specific animations or behaviors
}