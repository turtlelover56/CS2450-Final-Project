/*
    The Swing implementation and main method for PokéPath.
*/
import javax.swing.*;

public class AppRunner {
    AppRunner() {
        // Create the frame.
        JFrame jfrm = new JFrame("PokéPath! Definitely not copyrighted...");
        jfrm.setSize(1250, 800);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setResizable(false);

        // Build the starting menu.
        JPanel startPane = PaneBuilder.buildStartPanel(jfrm);
        // Build the battle menu.
        Player player = new Player(new InstanceEntity(new Entity("Player", 100, Entity.FIRE)));
        Enemy enemy = new Enemy(new InstanceEntity(new Entity("Enemy", 100, Entity.WATER)));
        JPanel battlePane = PaneBuilder.buildBattlePanel(jfrm, player, enemy);
        // Add startPane to the frame.
        
        jfrm.add(battlePane);

        // Make the frame visible.
        jfrm.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppRunner();
            }
        });
    }
}