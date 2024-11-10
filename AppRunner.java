/*
    The Swing implementation and main method for PokéPath.
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AppRunner {
    AppRunner() throws IOException {
        // Create the frame.
        JFrame jfrm = new JFrame("PokéPath! Definitely not copyrighted...");
        jfrm.setSize(1500, 800);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Fonts.
        Font normal = new Font("Serif", Font.PLAIN, 30);
        Font title = new Font("Serif", Font.BOLD, 55);

        // Create the components.
        JPanel startPane = new JPanel();
        startPane.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel("PokéPath");
        titleLabel.setFont(title);
        titleLabel.setForeground(Color.BLACK);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(-1, 1, 0, 15));
        JButton startButton = new JButton("Start");
        startButton.setFont(normal);
        JButton optionsButton = new JButton("Options");
        optionsButton.setFont(normal);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(normal);
        
        BufferedImage startBackground = ImageIO.read(new File("Backgrounds/start_background.png"));
        JLabel picLabel = new JLabel(new ImageIcon(startBackground));
        // Add the buttons to the buttonPanel.
        buttonPanel.add(startButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(exitButton);

        // Setup GridBagConstraints.
        JLabel invisibleLabel = new JLabel();
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, .4, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        startPane.add(invisibleLabel, c);
        invisibleLabel = new JLabel();
        c = new GridBagConstraints(2, 2, 1, 1, .4, .4, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        startPane.add(invisibleLabel, c);
        // titleLabel
        c = new GridBagConstraints(1, 0, 1, 1, .2, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        startPane.add(titleLabel, c);
        c = new GridBagConstraints(1, 1, 1, 1, .2, .3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
        startPane.add(buttonPanel, c);
        c = new GridBagConstraints(0, 0, 3, 3, .4, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        startPane.add(picLabel, c);

        // Add the panel to the frame.
        jfrm.add(startPane);

        // Make the frame visible.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {
        // Lambda expression (same as new Runnable() {...)
        SwingUtilities.invokeLater(() -> {
            try {
                new AppRunner();
            } catch (IOException e) {
                // TODO Auto-generated catch block

            }
        });
    }
}