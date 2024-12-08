public class Item extends Usable {
	private int count;

	// Constructors
	public Item(Usable usable) {
		this(usable, 1);
	}
	public Item(Usable usable, int count) {
		super(usable.getName(), usable.isTargetUser(), usable.isSwitchTarget(), usable.getEffect());
		this.count = count;
	}
	/* Changes count by 1. */
	public void increment() {
		changeCount(1);
	}
	/* Changes count by -1.
	 * Returns false if count reaches 0, else returns true. */
	public boolean decrement() {
		return changeCount(-1);
	}
	/* Changes count by the specified amount.
	 * Returns false if count <= 0, else returns true. */
	public boolean changeCount(int change) {
		count += change;
		return count <= 0;
	}

	// Getters/Setters
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	// toString
	@Override
	public String toString() {
		return "Item [count=" + count + ", usable=" + super.toString() + "]";
	}
}