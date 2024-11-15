
import java.util.ArrayList;
import java.util.List;

public class Entity {
    private String name;
    private int maxHealth;
    private List<Attack> attacks;

    // Constructors
    public Entity(int maxHealth, String name) {
        this.maxHealth = maxHealth;
        this.name = name;
    }
    public Entity(List<Attack> attacks, int maxHealth, String name) {
        this.attacks = attacks;
        this.maxHealth = maxHealth;
        this.name = name;
    }

    /* Returns a random attack from the enemy's attack list. */
    public Attack attack() {
        return attacks.get(roll(0, attacks.size() - 1));
    }

    /* Adds the specified attack to the attack list.
     * Returns 0 if successful, -1 if attack is already in the list. */
    public int addAttack(Attack attack) {
        if (attacks == null)
            attacks = new ArrayList<>();
        if (attacks.contains(attack))
            return -1;
        attacks.add(attack);
        return 0;
    }
    
    // Simulates rolling a die.
    protected int roll(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    // Getters/Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public List<Attack> getAttacks() {
        return attacks;
    }
    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

}
