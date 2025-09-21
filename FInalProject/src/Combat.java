import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combat {
    // Combat participants
    private Player player;
    private List<Enemy> enemies;
    
    // Combat state management
    private boolean combatActive;
    private boolean playerTurn;
    private int currentTurn;
    private int currentEnemyIndex;
    
    // Turn order management
    private List<Character> turnOrder; // Sorted by speed
    private int currentTurnIndex;
    
    // Combat results
    private boolean playerVictory;
    private boolean playerDefeated;
    private int totalExperienceGained;
    
    // Read user input from the console during combat
    private Scanner scanner;
    
    // Combat options/actions
    private enum CombatAction {
        ATTACK, CAST_SPELL, USE_ITEM, DEFEND, FLEE
    }
    
    public Combat(Player player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = new ArrayList<>(enemies);
        this.combatActive = true;
        this.currentTurn = 1;
        this.scanner = new Scanner(System.in);
        this.totalExperienceGained = 0;
        
        calculateTurnOrder();
    }
    
    private void calculateTurnOrder() {};
    private void handleEnemyDeath() {};
    private void calculatePhysicalDamage() {};
    
    
}