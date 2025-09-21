
public class Item {
	// Attributes
	private String name;
	private int inflictDamagePoints;
	private int defendDamagePoints;

	
	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInflictDamagePoints() {
		return inflictDamagePoints;
	}

	public void setInflictDamagePoints(int inflictDamagePoints) {
		this.inflictDamagePoints = inflictDamagePoints;
	}

	public int getDefendDamagePoints() {
		return defendDamagePoints;
	}

	public void setDefendDamagePoints(int defendDamagePoints) {
		this.defendDamagePoints = defendDamagePoints;
	}
	
	// Methods
	public void increaseInflictDamagePoints() {};
	public void increaseDefendDamagePoints() {};
}
