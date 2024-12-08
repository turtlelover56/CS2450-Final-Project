import javax.swing.JPanel;

public class Encounter {
	private Player player;
	private Enemy enemy;
	private JPanel battlePane;

    // Constructor
    public Encounter(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
        battlePane = null;
    }

	protected int resolveAttack(Attack attack, boolean targetEnemy) {
		if (Math.random() < attack.getAccuracy())
			return resolveEffect(attack.getEffect(), targetEnemy);
		else
			return -1;
	}

	protected int resolveItem(Item item, boolean targetEnemy) {
		if (!item.decrement())
			player.getInventory().remove(item);
		return resolveEffect(item.getEffect(), targetEnemy);
	}

	/** Resolves the effect on the given target.
	 * 
	 * @param effect The effect to be resolved.
	 * @param targetEnemy True if the target is the enemy, false if the target is the player.
	 * @return 0 if the attack hits but is not very effective, 1 if the attack hits normally, and 2 if the attack is very effective.
	 */
	protected int resolveEffect(Effect effect, boolean targetEnemy) {
		int result = 1;
		// Determine the target of the effect.
		InstanceEntity target = targetEnemy ? enemy : player;
		int power = effect.getPower();
		switch (effect.getGeneralType()) {
			// Damage
			case 0:
				int specificType = effect.getSpecificType();
				if (specificType < -2 || specificType > 3)
					System.out.println("Error: Specific Type " + effect.getSpecificType() + " not recognized for General Type " + effect.getGeneralType() + ".");
				else if (specificType != -1) {
					// Apply power bonus if effective against target type.
					int offset = specificType - 1;
					if (offset < 0)
						offset = 3;
					if (offset == target.getElement()) {
						power = (int) (power * 1.25);
						result = 2;
					}
					// Apply power debuff if not effective against target type.
					offset = specificType + 1;
					if (offset > 3)
						offset = 0;
					if (offset == target.getElement() || specificType == target.getElement()) {
						power = (int) (power * .75);
						result = 0;
					}
				}
				if (!effect.isPercent())
					target.changeCurrentHealth(-1 * power);
				else
					target.changeCurrentHealth(-1 * (int) (power / 100.0 * target.getMaxHealth()));
				break;
			// Boost
			case 1:
				// Not implemented!
				break;
			// Heal
			case 2:
				// Specific effect not implemented.
				if (!effect.isPercent())
					target.changeCurrentHealth(power);
				else
					target.changeCurrentHealth((int) (power / 100.0 * target.getMaxHealth()));	
				if (target.getCurrentHealth() > target.getMaxHealth())
					target.setCurrentHealth(target.getMaxHealth());
				break;
			// Unexpected type
			default:
				System.out.println("Error: General Type " + effect.getGeneralType() + " not recognized.");
		}
		return result;
	}

	/* Checks the status of the encounter. 
	 * Returns false if player or enemy's health is below or equal to 0.
	 * Else returns true. */
	protected boolean checkStatus() {
		return !(player.getCurrentHealth() <= 0 || enemy.getCurrentHealth() <= 0);
	}

	// Getters/Setters
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public JPanel getBattlePane() {
		return battlePane;
	}
	public void setBattlePane(JPanel battlePane) {
		this.battlePane = battlePane;
	}
}