
public class Spell {
    private String name;
    private int manaCost;
    private int damage;
    private String elementType; // "fire", "ice", "lightning", "heal", etc.
    private int cooldown;
    
    // Constructor
    public Spell(String name, int manaCost, int damage, String elementType, boolean isOffensive) {
    	this.name = name;
        this.manaCost = manaCost;
        this.damage = damage;
        this.elementType = elementType;
        this.cooldown = 0;
    }
    
    // Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
    
    // Methods
	
}
