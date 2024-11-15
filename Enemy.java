import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    private int currentHealth;
    private List<Attack> selectedAttacks; // This will (usually) be a subset of the entity's attack list.

    // Constructors
    /* Constructor with randomly selected attacks and currentHealth set to maxHealth. */
    public Enemy(Entity entity) {
        this(entity.getMaxHealth(), entity);
    }
    /* Constructor with randomly selected attacks. */
    public Enemy(int currentHealth, Entity entity) {
        super(entity.getAttacks(), entity.getMaxHealth(), entity.getName());
        this.currentHealth = currentHealth;
        // Roll for attacks in the entity to add to the InstanceEnemy's attacks.
        selectedAttacks = new ArrayList<>();
        int length = super.getAttacks().size();
        if (length > 3)
            length = 3;
        while(selectedAttacks.size() < length) {
            Attack selectedAttack = super.attack();
            if (!selectedAttacks.contains(selectedAttack))
                selectedAttacks.add(selectedAttack);
        }
    }
    public Enemy(List<Attack> selectedAttacks, int currentHealth, Entity entity) {
        super(entity.getAttacks(), entity.getMaxHealth(), entity.getName());
        this.selectedAttacks = selectedAttacks;
        this.currentHealth = currentHealth;
    }

    @Override
    public Attack attack() {
        return selectedAttacks.get(roll(0, selectedAttacks.size() - 1));
    }

    /* Method to change health by given amount. 
     * Returns true if health is > 0, else returns false.*/
    public boolean changeHealth(int change) {
        currentHealth += change;
        return currentHealth > 0;
    }

    // Getters/Setters
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public List<Attack> getselectedAttacks() {
        return selectedAttacks;
    }
    public void setSelectedAttacks(List<Attack> selectedAttacks) {
        this.selectedAttacks = selectedAttacks;
    }
}
