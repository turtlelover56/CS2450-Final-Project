import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PaneBuilder {
    // Fonts
    private static final Font NORMAL_FONT = new Font("Serif", Font.PLAIN, 30);
    private static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 55);
    private static final Color HEALTH_RED = new Color(255, 100, 100);
    
    /* Builds and returns the starting menu panel.
     * Must take jfrm in order for the button action listeners to work.
     */
    static JPanel buildStartPanel(JFrame jfrm) {
        // Create the components.
        JPanel startPane = new JPanel();
        startPane.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel("PokéPath");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.BLACK);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(-1, 1, 0, 15));
        JButton startButton = new JButton("Start");
        startButton.setFont(NORMAL_FONT);
        JButton optionsButton = new JButton("Options");
        optionsButton.setFont(NORMAL_FONT);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(NORMAL_FONT);
        // Add the buttons to the buttonPanel.
        buttonPanel.add(startButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(exitButton);

        // Set up GridBagConstraints.
        JLabel invisibleLabel = new JLabel(); // As placeholders for the grid.
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, .4, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        startPane.add(invisibleLabel, c);
        invisibleLabel = new JLabel();
        c = new GridBagConstraints(2, 2, 1, 1, .4, .4, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        startPane.add(invisibleLabel, c);
        // Add actual components.
        c = new GridBagConstraints(1, 0, 1, 1, .2, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        startPane.add(titleLabel, c);
        c = new GridBagConstraints(1, 1, 1, 1, .2, .3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
        startPane.add(buttonPanel, c);
        
        // Set up the background.
        try {
            c = new GridBagConstraints(0, 0, 3, 3, .4, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
            BufferedImage startBackground;
            startBackground = ImageIO.read(new File("Backgrounds/Start_Background.png"));
            JLabel picLabel = new JLabel(new ImageIcon(startBackground));
            startPane.add(picLabel, c);
        } catch (IOException ex) {
            
        }

        // Add action listeners to the buttons.
        startButton.addActionListener((ActionEvent ae) -> {
            // TODO start ActionListener
        });
        optionsButton.addActionListener((ActionEvent ae) -> {
            // TODO options ActionListener
        });
        exitButton.addActionListener((ActionEvent ae) -> {
            // Close the frame and exit the program.
            jfrm.dispose();
        });

        return startPane;
    }

    static JPanel buildBattlePanel(JFrame jfrm, Player player, Enemy enemy) {
        // Create the components.
        JPanel battlePane = new JPanel();
        battlePane.setLayout(new GridBagLayout());
        // Player components
        JLabel playerLabel = new JLabel(player.getName());
        playerLabel.setFont(NORMAL_FONT);
        playerLabel.setBackground(Color.WHITE);
        playerLabel.setOpaque(true);
        JProgressBar playerHealth = new JProgressBar(0, player.getMaxHealth());
        playerHealth.setValue(player.getCurrentHealth());
        playerHealth.setOpaque(false);
        playerHealth.setForeground(HEALTH_RED);
        JLabel playerIcon = new JLabel(player.getDefaultPose());
        playerIcon.setPreferredSize(new Dimension(300, 300));
        // Enemy components
        JLabel enemyLabel = new JLabel(enemy.getName());
        enemyLabel.setFont(NORMAL_FONT);
        enemyLabel.setBackground(Color.WHITE);
        enemyLabel.setOpaque(true);
        JProgressBar enemyHealth = new JProgressBar(0, enemy.getMaxHealth());
        enemyHealth.setValue(player.getCurrentHealth());
        enemyHealth.setOpaque(false);
        enemyHealth.setForeground(HEALTH_RED);
        JLabel enemyIcon = new JLabel(enemy.getDefaultPose());
        enemyIcon.setPreferredSize(new Dimension(300, 300));
        // Option buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,1, 0, 10));
        buttonPanel.setOpaque(false);
        JButton attackButton = new JButton("Attack");
        attackButton.setFont(NORMAL_FONT);
        JButton itemsButton = new JButton("Items");
        itemsButton.setFont(NORMAL_FONT);
        JButton runButton = new JButton("Run");
        runButton.setFont(NORMAL_FONT);
        // TODO: Attack and Items Lists and Run confirmation.
        // Add the buttons to the buttonPanel.
        buttonPanel.add(attackButton);
        buttonPanel.add(itemsButton);
        buttonPanel.add(runButton);

        // Set up GridBagConstraints.
        JLabel invisibleLabel = new JLabel(); // As placeholders for the grid.
        GridBagConstraints c = new GridBagConstraints(0, 4, 1, 1, .1, .1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,5,0,0), 0, 0);
        battlePane.add(playerLabel, c);
        c = new GridBagConstraints(0, 5, 2, 1, .2, .1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,5,10,0), 0, 0);
        battlePane.add(playerHealth, c);
        c = new GridBagConstraints(2, 3, 2, 1, .4, .4, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        battlePane.add(playerIcon, c);
        // c = new GridBagConstraints(1, 3, 1, 1, .125, .33, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
        // battlePane.add(invisibleLabel, c);
        c = new GridBagConstraints(7, 0, 1, 1, .1, .1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,5), 0, 0);
        battlePane.add(enemyLabel, c);
        c = new GridBagConstraints(6, 1, 2, 1, .2, .1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,5), 0, 0);
        battlePane.add(enemyHealth, c);
        c = new GridBagConstraints(4, 2, 2, 1, .4, .4, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        battlePane.add(enemyIcon, c);
        c = new GridBagConstraints(6, 3, 2, 3, .2, .5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(30,20,10,10), 0, 0);
        battlePane.add(buttonPanel, c);

        // Set up the background.
        // Not Working
        try {
            c = new GridBagConstraints(0, 0, 8, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
            BufferedImage battleBackground;
            battleBackground = ImageIO.read(new File("Backgrounds/Battle_Background.png"));
            JLabel picLabel = new JLabel(new ImageIcon(battleBackground));
            battlePane.add(picLabel, c);
        } catch (IOException ex) {
            
        }
    
        return battlePane;
    }
}