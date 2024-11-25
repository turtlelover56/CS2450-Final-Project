import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaneBuilder {
    // Fonts
    private static final Font NORMAL_FONT = new Font("Serif", Font.PLAIN, 30);
    private static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 55);
    private static final Color HEALTH_RED = new Color(255, 100, 100);

    //Buttons
    private static JButton itemsButton;
    private static JButton runButton;
    //Counters For Items
    private static int item1Count;
    private static int item2Count;
        
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
            
            JProgressBar playerHealth = new CustomProgressBar();
            playerHealth.setValue(player.getCurrentHealth());
            playerHealth.setOpaque(false);

            playerHealth.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int value = playerHealth.getValue();
                    playerHealth.setString(String.valueOf(value)); // Show number only
                }
            });

            JLabel playerIcon = new JLabel(player.getDefaultPose());
            playerIcon.setPreferredSize(new Dimension(300, 300));
            // Enemy components
            JLabel enemyLabel = new JLabel(enemy.getName());
            enemyLabel.setFont(NORMAL_FONT);
            enemyLabel.setBackground(Color.WHITE);
            enemyLabel.setOpaque(true);
            JProgressBar enemyHealth = new CustomProgressBar();
            enemyHealth.setValue(player.getCurrentHealth());
            enemyHealth.setOpaque(false);
            
            enemyHealth.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int value = enemyHealth.getValue();
                    enemyHealth.setString(String.valueOf(value)); // Show number only
                }
            });
            
            
            JLabel enemyIcon = new JLabel(enemy.getDefaultPose());
            enemyIcon.setPreferredSize(new Dimension(300, 300));
            // Option buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(0,2, 0, 10));
            buttonPanel.setOpaque(false);
            
            /*Create Action Buttons
             * When Attack/Item is Selected Set to Visible
             * w/ Corresponding Label
             */
            JButton action1 = new JButton("");
            action1.setVisible(false);
            action1.setFont(NORMAL_FONT);
            
            //Action Listener For Action1
            action1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent le) {
                    int health = enemyHealth.getValue();
                    if(action1.getText() == "Attack 1") {
                        enemyHealth.setValue(health - 5);
                    } else if ((action1.getText() == "Item 1") && (item1Count != 1)) {
                        enemyHealth.setValue(health - 10);
                        action1.setEnabled(false);
                        item1Count++;   
                    }
                }
            });
            
            JButton action2 = new JButton("");
            action2.setVisible(false);
            action2.setFont(NORMAL_FONT);
            action2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent le) {
                    int health = playerHealth.getValue();
                    if(action2.getText() == "Attack 2") {
                        playerHealth.setValue(health - 10);
                    } else if ((action2.getText() == "Item 2") && (item2Count != 1)) {
                        playerHealth.setValue(health + 20);
                        item2Count++;
                        action2.setEnabled(false);
                    }
                }
            });
            JButton action3 = new JButton("");
            action3.setVisible(false);
            action3.setFont(NORMAL_FONT);

            //Attack Button
            JButton attackButton = new JButton("Attack");
            attackButton.setFont(NORMAL_FONT);
            
            //Action Listener For Attack Button
            attackButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent le) {
                    action1.setEnabled(true);
                    action2.setEnabled(true);
                    if(!action1.isVisible()) {
                    //Set Action Buttons to Visible
                    action1.setVisible(true);
                    action2.setVisible(true);
                    action3.setVisible(true);

                    //Add Corresponding Labels
                    action1.setText("Attack 1");
                    action2.setText("Attack 2");
                    action3.setText("Attack 3");
                    } else {
                        action1.setVisible(false);
                        action2.setVisible(false);
                        action3.setVisible(false); 
                    }
                }
            });
    
    
        itemsButton = new JButton("Items");
        itemsButton.setFont(NORMAL_FONT);

        itemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                if (item1Count == 1) {
                    action1.setEnabled(false);
                } else if (item2Count == 2) {
                    action2.setEnabled(false);
                }
                if(!action1.isVisible()) {
                //Set Action Buttons to Visible
                action1.setVisible(true);
                action2.setVisible(true);
                action3.setVisible(true);

                //Add Corresponding Labels
                action1.setText("Item 1");
                action2.setText("Item 2");
                action3.setText("Item 3");
                } else {
                    action1.setVisible(false);
                    action2.setVisible(false);
                    action3.setVisible(false); 
                }
            }
        });
        runButton = new JButton("Run");
        runButton.setFont(NORMAL_FONT);
            runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                System.exit(0);
            }
        });
        // TODO: Attack and Items Lists and Run confirmation.
        // Add the buttons to the buttonPanel.
        buttonPanel.add(action1);
        buttonPanel.add(attackButton);
        buttonPanel.add(action2);
        buttonPanel.add(itemsButton);
        buttonPanel.add(action3);
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

class CustomProgressBar extends JProgressBar {
    @Override
    protected void paintComponent(Graphics g) {
        // Get the current value of the progress bar
        int value = getValue();
        Graphics2D g2d = (Graphics2D) g;

        // Draw the background (this is the "empty" part of the progress bar)
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set color based on progress value
        if (value <= 20) {
            g2d.setColor(new Color(255, 100, 100)); // Color red for values < 20
        } else {
            g2d.setColor(Color.GREEN); // Color green for values >= 20
        }

        // Paint the progress (the filled portion of the progress bar)
        int progressWidth = (int) (getWidth() * ((double) value / getMaximum()));
        g2d.fillRect(0, 0, progressWidth, getHeight());

        // Draw the string text (percentage or number)
        g2d.setColor(getForeground());
        g2d.drawString(getString(), getWidth() / 2 - 10, getHeight() / 2 + 5); // Draw text in the center
        
    }
}
