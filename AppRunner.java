/*
    The Swing implementation and main method for PokéPath.
*/
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppRunner {
    AppRunner() {
        // Create the frame.
        JFrame jfrm = new JFrame("PokéPath! Definitely not copyrighted...");
        jfrm.setSize(1500, 800);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setResizable(false);
        CardLayout cards = new CardLayout();
        jfrm.setLayout(cards);

        // Build the starting menu.
        JPanel startPane = PaneBuilder.buildStartPanel(jfrm, cards);
        // Build the battle menu.
        Player player = new Player(new InstanceEntity(new Entity("Player", 100, Entity.FIRE)));
        java.util.List<Attack> attacks = new ArrayList<>();
        attacks.add(new Attack(new Usable("Whack", false, false, new Effect(0, -1, 10, false))));
        player.setSelectedAttacks(attacks);
        player.setAttacks(attacks);
        player.addItem(new Item(new Usable("Health Potion", true, false, new Effect(2, -1, 50, true))));
        Enemy enemy = new Enemy(new InstanceEntity(new Entity("Enemy", 100, Entity.WATER)));
        enemy.setAttacks(attacks);
        enemy.setSelectedAttacks(attacks);
        Encounter encounter = new Encounter(enemy, player);
        JPanel battlePane = PaneBuilder.buildBattlePanel(jfrm, encounter);
        encounter.setBattlePane(battlePane);
        // Build the death screen.
        JPanel deathPane = PaneBuilder.buildDeathPanel(jfrm, cards);
        
        // Add the panes to the frame.
        jfrm.add(startPane, "Start");
        jfrm.add(battlePane, "Battle");
        jfrm.add(deathPane, "Death");

        // Debugging tool to change screens instantly.
        //changeScreen(cards, jfrm.getContentPane(), "Death");

        // Make the frame visible.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {
        // Lambda expression (same as new Runnable() {...)
        SwingUtilities.invokeLater(() -> {
            new AppRunner();
        });
    }

    public static void changeScreen(CardLayout cards, Container container, String string) {
        // Changes screen to whichever screen is specified in "string"
        cards.show(container, string);
    }
}