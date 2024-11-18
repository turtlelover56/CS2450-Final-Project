
public class Enemy extends InstanceEntity {
    private int intelligence;

    // Constructors
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
}
