import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    private int currentHealth;
    private List<Attack> selectedAttacks;
    private List<ItemEntry> inventory;

    // Constructors
    /* Constructor with randomly selected attacks, with currentHealth set to maxHealth. */
    public Player(Entity entity) {
        this(entity.getMaxHealth(), entity);
    }
    /* Constructor with randomly selected attacks. */
    public Player(int currentHealth, Entity entity) {
        super(entity.getAttacks(), entity.getMaxHealth(), entity.getName());
        this.currentHealth = currentHealth;
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
    /* Constructor with currentHealth set to maxHealth. */
    public Player(List<Attack> selectedAttacks, Entity entity) {
        this(selectedAttacks, entity.getMaxHealth(), entity);
    }
    public Player(List<Attack> selectedAttacks, int currentHealth, Entity entity) {
        super(entity.getAttacks(), entity.getMaxHealth(), entity.getName());
        this.selectedAttacks = selectedAttacks;
        this.currentHealth = currentHealth;
    }

    /* Method to change health by given amount. 
     * Returns true if health is > 0, else returns false.*/
    public boolean changeHealth(int change) {
        currentHealth += change;
        return currentHealth > 0;
    }

    /* Adds the specified item to the inventory.
     * If the item already exists, increments its count.
     * Returns 0 if added, 1 if incremented. */
    public int addItem(Item item) {
        for (ItemEntry entry : inventory)
            if (entry.getItem().equals(item)) {
                entry.incrementCount();
                return 1;
            }
        inventory.add(new ItemEntry(item));
        return 0;
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
    public List<ItemEntry> getInventory() {
        return inventory;
    }
    public void setInventory(List<ItemEntry> inventory) {
        this.inventory = inventory;
    }

}
