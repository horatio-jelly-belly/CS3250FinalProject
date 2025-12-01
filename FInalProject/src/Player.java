/**
 * Represents the player's character in the game.
 * Extends Character to inherit common stats and behaviors.
 */
public class Player extends Character {

    /**
     * Constructor to create the player character with specified stats.
     * Passes all parameters to the parent Character constructor.
     * @param maxHitPoints Maximum and starting health
     * @param attackPoints Damage dealing capability  
     * @param defense Damage reduction capability
     * @param speed Turn order/movement speed
     */
    public Player(int maxHitPoints, int attackPoints, int defense) {
        super(maxHitPoints, attackPoints, defense);
    }
}