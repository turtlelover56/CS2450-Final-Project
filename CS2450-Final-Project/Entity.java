import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Entity {
    private String name;
    private int maxHealth;
    private int element;
    /*  0 - Fire
        1 - Water
        2 - Electricity
        3 - Wood */
    private List<Attack> attacks;
    private ImageIcon defaultPose;
    private ImageIcon attackPose;
    private static final ImageIcon TEMP_DEFAULT_POSE = new ImageIcon("Sprites//Temp_Sprite_Default.png");
    private static final ImageIcon TEMP_ATTACK_POSE = new ImageIcon("Sprites//Temp_Sprite_Attack.png");

    // Constants
    public static final int FIRE = 0;
    public static final int WATER = 1;
    public static final int ELECTRICITY = 2;
    public static final int WOOD = 3;
    
    // Constructors
    public Entity(String name, int maxHealth, int element) {
        this(name, maxHealth, element, new ArrayList<>());
    }
    public Entity(String name, int maxHealth, int element, List<Attack> attacks) {
        this(name, maxHealth, element, attacks, TEMP_DEFAULT_POSE, TEMP_ATTACK_POSE);
    }
    public Entity(String name, int maxHealth, int element, List<Attack> attacks, ImageIcon defaultPose, ImageIcon attackPose) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.element = element;
        this.attacks = attacks;
        this.defaultPose = defaultPose;
        this.attackPose = attackPose;
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
    public int getElement() {
        return element;
    }
    public void setElement(int element) {
        this.element = element;
    }
    public List<Attack> getAttacks() {
        return attacks;
    }
    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }
    public ImageIcon getDefaultPose() {
        return defaultPose;
    }
    public void setDefaultPose(ImageIcon defaultPose) {
        this.defaultPose = defaultPose;
    }
    public ImageIcon getAttackPose() {
        return attackPose;
    }
    public void setAttackPose(ImageIcon attackPose) {
        this.attackPose = attackPose;
    }

    // toString
    @Override
    public String toString() {
        return "Entity [name=" + name + ", maxHealth=" + maxHealth + ", element=" + element + ", attacks=" + attacks
                + ", defaultPose=" + defaultPose + ", attackPose=" + attackPose + "]";
    }
}
