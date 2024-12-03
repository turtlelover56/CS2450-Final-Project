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

    public boolean runEncounter() {
        if (roll(0, 1) == 1);
            enemyTurn();
        boolean running = checkStatus();
        while (running) {
            if (!playerTurn())
                break; // Bad Practice :(
            if (battlePane != null)
                PaneBuilder.enableComponents(battlePane, false);
            if (!enemyTurn())
                break;
            if (battlePane != null)
                PaneBuilder.enableComponents(battlePane, true);
        }
        if (player.getCurrentHealth() <= 0)
            return false;
        else
            return true;
    }

    protected boolean resolveAttack(Attack attack, boolean targetEnemy) {
        return checkStatus();
    }

    protected boolean resolveItem(Item item, boolean targetEnemy) {
        return checkStatus();
    }

    protected boolean playerTurn() {
        return checkStatus();
    }

    protected boolean enemyTurn() {
        return checkStatus();
    }

    /* Checks the status of the encounter. 
     * Returns false if player or enemy's health is below or equal to 0.
     * Else returns true. */
    private boolean checkStatus() {
        return !(player.getCurrentHealth() <= 0 || enemy.getCurrentHealth() <= 0);
    }
    // Simulates rolling a die.
    private int roll(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
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