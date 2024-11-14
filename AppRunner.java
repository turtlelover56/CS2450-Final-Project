/*
    The Swing implementation and main method for PokéPath.
*/
import javax.swing.*;

public class AppRunner {
    AppRunner() {
        // Create the frame.
        JFrame jfrm = new JFrame("PokéPath! Definitely not copyrighted...");
        jfrm.setSize(1500, 800);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setResizable(false);

        // Build the starting menu.
        JPanel startPane = PaneBuilder.buildStartPanel(jfrm);

        // Add startPane to the frame.
        jfrm.add(startPane);

        // Make the frame visible.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {
        // Lambda expression (same as new Runnable() {...)
        SwingUtilities.invokeLater(() -> {
            new AppRunner();
        });
    }
}