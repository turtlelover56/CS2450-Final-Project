public class ItemEntry {
    private Item item;
    private int count;

    // Constructors
    public ItemEntry(Item item) {
        this.item = item;
        count = 1;
    }
    public ItemEntry(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    /* Increments the item count. */
    public void incrementCount() {
        count++;
    }
    /* Changes the item count by the specified amount. */
    public void changeCount(int change) {
        count += change;
        if (count < 0)
            count = 0;
    }

    // Getters/Setters
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}