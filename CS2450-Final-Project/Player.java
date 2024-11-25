import java.util.ArrayList;
import java.util.List;

public class Player extends InstanceEntity {
    private List<Item> inventory;

    // Constructors
    public Player(InstanceEntity instanceEntity) {
        this(new ArrayList<>(), instanceEntity);
    }
    public Player(List<Item> inventory, InstanceEntity instanceEntity) {
        super(instanceEntity.getCurrentHealth(), instanceEntity.getSelectedAttacks(), instanceEntity);
        this.inventory = inventory;
    }

    /* Adds the specified item to the inventory.
     * If the item already exists, adds to its count.
     * Returns 0 if added, 1 if count changed. */
    public int addItem(Item newItem) {
        for (Item item : inventory)
            if (item.getName().equals(newItem.getName())) {
                item.changeCount(newItem.getCount());
                return 1;
            }
        inventory.add(newItem);
        return 0;
    }
    /* Adds the specified item to the inventory.
     * If the item already exists, increment its count.
     * Returns 0 if added, 1 if count incremented. */
    public int addItem(Usable usable) {
        for (Item item : inventory)
            if (item.getName().equals(usable.getName())) {
                item.increment();
                return 1;
            }
        inventory.add(new Item(usable));
        return 0;
    }

    // Getters/Setters
    public List<Item> getInventory() {
        return inventory;
    }
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

}