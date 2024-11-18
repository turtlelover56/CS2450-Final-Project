
import java.util.ArrayList;
import java.util.List;

public class InstanceEntity extends Entity {
    private int currentHealth;
    private List<Attack> selectedAttacks;

    // Constructors
    public InstanceEntity(Entity entity) {
        this(null, entity);
        // Roll for attacks in the entity to add to the InstanceEnemy's attacks.
        selectedAttacks = new ArrayList<>();
        int length = getAttacks().size();
        if (length > 3)
            length = 3;
        while(selectedAttacks.size() < length) {
            Attack selectedAttack = super.attack();
            if (!selectedAttacks.contains(selectedAttack))
                selectedAttacks.add(selectedAttack);
        }
    }
    public InstanceEntity(List<Attack> selectedAttacks, Entity entity) {
        this(entity.getMaxHealth(), selectedAttacks, entity);
    }
    public InstanceEntity(int currentHealth, List<Attack> selectedAttacks, Entity entity) {
        super(entity.getName(), entity.getMaxHealth(), entity.getAttacks(), entity.getDefaultPose(), entity.getAttackPose());
        this.currentHealth = currentHealth;
        this.selectedAttacks = selectedAttacks;
    }

    /* Changes current health by the specified amount.
     * Returns false if currentHealth <= 0, else returns true. */
    public boolean changeCurrentHealth(int change) {
        currentHealth += change;
        return currentHealth <= 0;
    }

    @Override
    public Attack attack() {
        return selectedAttacks.get(roll(0, selectedAttacks.size() - 1));
    }

    // Getters/Setters
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public List<Attack> getSelectedAttacks() {
        return selectedAttacks;
    }
    public void setSelectedAttacks(List<Attack> selectedAttacks) {
        this.selectedAttacks = selectedAttacks;
    }
}