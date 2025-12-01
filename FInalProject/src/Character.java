/**
 * Base class for all game characters (Player and Enemy).
 * Defines common attributes and behaviors shared by all characters in the game.
 * Uses inheritance to avoid code duplication between Player and Enemy classes.
 */
public class Character {
    // Attributes
	
    /**
     * Current health points of the character.
     * When this reaches 0, the character is defeated.
     */
    private int hitPoints;
    
    /**
     * Maximum health points the character can have.
     * Used as the starting value.
     */
    private int maxHitPoints;
    
    /**
     * Offensive power of the character.
     * Higher values mean more damage dealt to opponents during attacks.
     * Used in damage calculation formulas during combat.
     */
    private int attackPoints;
    
    /**
     * Defensive capability of the character.
     * Reduces incoming damage from enemy attacks.
     * Higher defense means less damage taken.
     */
    private int defense;
    
    /**
     * Flag indicating if the character is still active in combat.
     * Set to false when hitPoints reaches 0.
     * Remove character when isAlive is false
     */
    private boolean isAlive;
    
    /**
     * Constructor to create a new character with specified stats.
     * Initializes hitPoints to maximum and sets character as alive.
     * @param maxHitPoints Maximum and starting health
     * @param attackPoints Offensive power
     * @param defense Defensive capability
     * @param speed Action/movement speed
     */
    public Character(int maxHitPoints, int attackPoints, 
            int defense) {
        this.maxHitPoints = maxHitPoints;
        this.hitPoints = maxHitPoints;  // Start at full health
        this.attackPoints = attackPoints;
        this.defense = defense;
        this.isAlive = true;  // Characters start alive
    }
    
    // Getters and setters
    
    /**
     * Gets current health points.
     * Used for displaying health in UI and checking defeat conditions.
     * @return Current hit points value
     */
    public int getHitPoints() {
        return hitPoints;
    }
    
    /**
     * Gets maximum health points.
     * Used for health bar displays and healing limits.
     * @return Maximum hit points value
     */
    public int getMaxHitPoints() {
        return maxHitPoints;
    }
    
    /**
     * Gets attack power.
     * Used in damage calculations during combat.
     * @return Attack points value
     */
    public int getAttackPoints() {
        return attackPoints;
    }
    
    /**
     * Gets defensive capability.
     * Used to reduce incoming damage in combat calculations.
     * @return Defense value
     */
    public int getDefense() {
        return defense;
    }
    
    /**
     * Checks if character is still alive.
     * Used to determine if character can act or if combat is over.
     * @return true if alive, false if defeated
     */
    public boolean isAlive() {
        return isAlive;
    }
    
    // Methods to change statistics
    
    /**
     * Processes damage taken by the character.
     * Empty method to be overridden by subclasses (Player/Enemy).
     * Reduces hitPoints and check for defeat.
     */
    public void takeDamage(int attackPoints) {
    	reduceHitPoints(attackPoints);
    };
    
    protected void reduceHitPoints(int amount) {
    	this.hitPoints -= amount;
    	if (this.hitPoints <= 0) {
    		this.hitPoints = 0;
    		this.isAlive = false;
    	}
    }
}