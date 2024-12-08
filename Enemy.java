import java.util.List;

public class Enemy extends InstanceEntity {
	private int intelligence;

	// Constructors
	public Enemy(Entity entity) {
		super(entity);
		intelligence = 0;
	}
	public Enemy(InstanceEntity instanceEntity) {
		this(0, instanceEntity);
	}
	public Enemy(int intelligence, InstanceEntity instanceEntity) {
		super(instanceEntity.getCurrentHealth(), instanceEntity.getSelectedAttacks(), instanceEntity);
		this.intelligence = intelligence;
	}

	/* Method to change health by given amount. 
	 * Returns true if health is > 0, else returns false.*/
	public boolean changeHealth(int change) {
		return changeCurrentHealth(change);
	}

	// Getters/Setters
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

    @Override
    public String toString() {
        return "Enemy [intelligence=" + intelligence + ", instance entity=" + super.toString() + "]";
    }
	
    // Method to generate a new enemy
	public Enemy generateNewEnemy(List<Entity> entityDex) {
		Enemy enemy = null;
		while (enemy == null) {
			int roll = (int) (Math.random() * entityDex.size());
			Entity chosenEntity = entityDex.get(roll);
			if (!chosenEntity.getName().equals("Player"))
				enemy = new Enemy(chosenEntity);
		}
		return enemy;
	}

}

