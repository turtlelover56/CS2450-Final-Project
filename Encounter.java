
import javax.swing.SwingUtilities;

public class Encounter {
    private Player player;
    private Enemy enemy;

    // Constructor
    public Encounter(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }

    public boolean runEncounter() {
        if (roll(0, 1) == 1);
            enemyTurn();
        boolean running = checkStatus();
        while (running) {
            playerTurn();
            enemyTurn();
        }
        // TODO: End of round events.
    }

    private boolean playerTurn() {
        
    }

    private boolean enemyTurn() {

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
}