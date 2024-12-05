import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class PaneBuilder {
    // Fonts
    private static final Font NORMAL_FONT = new Font("Serif", Font.PLAIN, 30);
    private static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 55);
    private static final Color HEALTH_RED = new Color(255, 100, 100);
    
    /* Builds and returns the starting menu panel.
     * Must take jfrm in order for the button action listeners to work.
     */
    static JPanel buildStartPanel(JFrame jfrm, CardLayout cards) {
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
            System.out.println("Unable to load background :(");
        }

        // Add action listeners to the buttons.
        startButton.addActionListener((ActionEvent ae) -> {
            // Changes the screen to the battle screen.
            AppRunner.changeScreen(cards, jfrm.getContentPane(), "Battle");
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

    static JPanel buildBattlePanel(JFrame jfrm, CardLayout cards, Encounter encounter) {
        Player player = encounter.getPlayer();
        Enemy enemy = encounter.getEnemy();
        // Create the components.
        JPanel battlePane = new JPanel();
        battlePane.setLayout(new GridBagLayout());
        // Action scrollpane
        JTextArea actionTextArea = new JTextArea();
        actionTextArea.setEditable(false);
        actionTextArea.setLineWrap(true);
        JScrollPane actionScrollPane = new JScrollPane(actionTextArea);
        actionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
        enemyHealth.setValue(enemy.getCurrentHealth());
        enemyHealth.setOpaque(false);
        enemyHealth.setForeground(HEALTH_RED);
        JLabel enemyIcon = new JLabel(enemy.getDefaultPose());
        enemyIcon.setPreferredSize(new Dimension(300, 300));
        // Attack List
        java.util.List<Attack> attacks = player.getSelectedAttacks();
        DefaultListModel<String> attackModel = new DefaultListModel<>();
        for (Attack attack : attacks)
            attackModel.addElement(attack.getName());
        JList<String> attackList = new JList<>(attackModel);
        attackList.addListSelectionListener((ListSelectionEvent lse) -> {
            if (!attackList.isSelectionEmpty()) {
                String attackName = attackList.getSelectedValue();
                int result = -2;
                for (Attack attack : player.getSelectedAttacks()) {
                    if (attack.getName().equals(attackName)) {
                        result = encounter.resolveAttack(attack, !attack.isTargetUser());
                        // Add the action to the action log.
                        updateActionLog(actionTextArea, attack, result, player);
                    }
                }
                // Update health bars.
                playerHealth.setValue(player.getCurrentHealth());
                enemyHealth.setValue(enemy.getCurrentHealth());
                attackList.clearSelection();
                // Run the enemy's turn.
                Attack enemyAttack = enemy.attack();
                result = encounter.resolveAttack(enemyAttack, enemyAttack.isTargetUser());
                // Add the action to the action log.
                updateActionLog(actionTextArea, enemyAttack, result, enemy);
                // Update health bars.
                playerHealth.setValue(player.getCurrentHealth());
                enemyHealth.setValue(enemy.getCurrentHealth());
            }
        });
        JScrollPane attackScrollPane = new JScrollPane(attackList);
        //attackScrollPane.setVisible(false);
        // Item List
        JList<String> itemList = new JList<>();
        updateInventory(itemList, player);
        itemList.addListSelectionListener((ListSelectionEvent lse) -> {
            if (!itemList.isSelectionEmpty()) {
                String itemName = itemList.getSelectedValue();
                int result = -2;
                for (Item item : player.getInventory())
                    if (item.getName().equals(itemName)) {
                        result = encounter.resolveItem(item, !item.isTargetUser());
                        updateInventory(itemList, player);
                        // Add the action to the action log.
                        updateActionLog(actionTextArea, item, result, player);
                        break; // Bad practice :(
                    }
                    
                // Update health bars.
                playerHealth.setValue(player.getCurrentHealth());
                enemyHealth.setValue(enemy.getCurrentHealth());
                itemList.clearSelection();
                // Run the enemy's turn.
                Attack enemyAttack = enemy.attack();
                result = encounter.resolveAttack(enemyAttack, enemyAttack.isTargetUser());
                // Add the action to the action log.
                updateActionLog(actionTextArea, enemyAttack, result, enemy);           
                // Update health bars.
                playerHealth.setValue(player.getCurrentHealth());
                enemyHealth.setValue(enemy.getCurrentHealth());
            }
        });
        JScrollPane itemScrollPane = new JScrollPane(itemList);
        //itemScrollPane.setVisible(false);
        // Option buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,1, 0, 10));
        buttonPanel.setOpaque(false);
        JButton attackButton = new JButton("Attack");
        attackButton.setFont(NORMAL_FONT);
        attackButton.addActionListener((ActionEvent ae) -> {
            itemScrollPane.setVisible(false);
            attackScrollPane.setVisible(!attackScrollPane.isVisible());
        });
        JButton itemsButton = new JButton("Items");
        itemsButton.addActionListener((ActionEvent ae) -> {
            attackScrollPane.setVisible(false);
            itemScrollPane.setVisible(!itemScrollPane.isVisible());
        });
        itemsButton.setFont(NORMAL_FONT);
        JButton runButton = new JButton("Run");
        runButton.setFont(NORMAL_FONT);
        // TODO: Run confirmation



        // Add the buttons to the buttonPanel.
        buttonPanel.add(attackButton);
        buttonPanel.add(itemsButton);
        buttonPanel.add(runButton);

        // Set up GridBagConstraints.
        GridBagConstraints c = new GridBagConstraints(0, 4, 1, 1, .1, .1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,5,0,0), 0, 0);
        battlePane.add(playerLabel, c);
        c = new GridBagConstraints(0, 5, 2, 1, .2, .1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,5,10,0), 0, 0);
        battlePane.add(playerHealth, c);
        c = new GridBagConstraints(2, 3, 2, 1, .4, .4, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        battlePane.add(playerIcon, c);
        c = new GridBagConstraints(7, 0, 1, 1, .1, .1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,5), 0, 0);
        battlePane.add(enemyLabel, c);
        c = new GridBagConstraints(6, 1, 2, 1, .2, .1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,5), 0, 0);
        battlePane.add(enemyHealth, c);
        c = new GridBagConstraints(4, 2, 2, 1, .4, .4, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        battlePane.add(enemyIcon, c);
        c = new GridBagConstraints(6, 3, 2, 3, .2, .5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(30,20,10,10), 0, 0);
        battlePane.add(buttonPanel, c);
        c = new GridBagConstraints(0, 0, 4, 3, .8, .2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5,5,250,475), 0, 0);
        battlePane.add(actionScrollPane, c);
        c = new GridBagConstraints(5, 3, 1, 3, .1, .5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(30,450,10,0), 0, 0);
        battlePane.add(attackScrollPane, c);
        c = new GridBagConstraints(5, 3, 1, 3, .1, .5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(30,450,10,0), 0, 0);
        battlePane.add(itemScrollPane, c);

        // Set up the background.
        try {
            c = new GridBagConstraints(0, 0, 8, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
            BufferedImage battleBackground;
            int element = enemy.getElement();
            String[] fileNames = {"Backgrounds/fire_background.png", "Backgrounds/water_background.png", "Backgrounds/lightning_background.png", "Backgrounds/wood_background.png"};
            battleBackground = ImageIO.read(new File(fileNames[element]));
            JLabel picLabel = new JLabel(new ImageIcon(battleBackground));
            battlePane.add(picLabel, c);
        } catch (IOException ex) {
            System.out.println("Unable to load background :(");
        }
    
        return battlePane;
    }

    static JPanel buildIntermissionPanel(JFrame jfrm, CardLayout cards, Intermission intermission, Player player) {
        // Create the components
        JPanel intermissionPane = new JPanel(new GridBagLayout());
        // Label
        JLabel intermissionLabel = new JLabel("<html>" + intermission.getPrompt());
        intermissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        intermissionLabel.setBorder(BorderFactory.createEtchedBorder());
        intermissionLabel.setOpaque(true);
        intermissionLabel.setMinimumSize(new Dimension(800, 300));
        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setOpaque(false);
        // Option Buttons
        JButton optionOneButton = new JButton(intermission.getOptionOne());
        buttonPanel.add(optionOneButton);
        JButton optionTwoButton = new JButton(intermission.getOptionTwo());
        buttonPanel.add(optionTwoButton);
        // Next Button
        JButton nextButton = new JButton("Next Encounter");
        buttonPanel.add(nextButton);
        nextButton.setVisible(false);
        // Button Action Listeners
        optionOneButton.addActionListener((ActionEvent ae) -> {
            intermissionLabel.setText("<html>" + intermission.getResultOne());
            optionOneButton.setVisible(false);
            optionTwoButton.setVisible(false);
            nextButton.setVisible(true);
            player.addItem(new Item(intermission.getOptionOneItem(), intermission.getCountOne()));
        });
        optionTwoButton.addActionListener((ActionEvent ae) -> {
            intermissionLabel.setText("<html>" + intermission.getResultTwo());
            optionOneButton.setVisible(false);
            optionTwoButton.setVisible(false);
            nextButton.setVisible(true);
            player.addItem(new Item(intermission.getOptionTwoItem(), intermission.getCountTwo()));
        });
        nextButton.addActionListener((ActionEvent ae) -> {
            AppRunner.changeScreen(cards, jfrm.getContentPane(), "Battle");
        });
        // Set up GridBagConstraints.
        JLabel invisibleLabel = new JLabel(); // As placeholders for the grid.
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, .2, .1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
        intermissionPane.add(invisibleLabel, c);
        invisibleLabel = new JLabel();
        c = new GridBagConstraints(4, 2, 1, 1, .2, .2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0);
        intermissionPane.add(invisibleLabel, c);
        c = new GridBagConstraints(1, 1, 3, 1, .3, .6, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        intermissionPane.add(intermissionLabel, c);
        c = new GridBagConstraints(2, 2, 1, 1, .3, .3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20,0,20,0), 0, 0);
        intermissionPane.add(buttonPanel, c);

        try {
            c = new GridBagConstraints(0, 0, 5, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
            BufferedImage startBackground;
            startBackground = ImageIO.read(new File("Backgrounds/Start_Background.png"));
            JLabel picLabel = new JLabel(new ImageIcon(startBackground));
            intermissionPane.add(picLabel, c);
        } catch (IOException ex) {
            System.out.println("Unable to load background :(");
        }

        return intermissionPane;
    }

    static JPanel buildDeathPanel(JFrame jfrm, CardLayout cards) {
        // Create the components.
        JPanel deathPane = new JPanel(new GridBagLayout());
        deathPane.setBackground(Color.BLACK);
        // Death label
        JLabel deathLabel = new JLabel("You died.");
        deathLabel.setFont(TITLE_FONT);
        deathLabel.setForeground(Color.RED);
        // Return to menu button
        JButton returnButton = new JButton("Return to Menu?");
        returnButton.setFont(NORMAL_FONT);
        returnButton.setBackground(Color.LIGHT_GRAY);
        //returnButton.setForeground(Color.BLACK);
        returnButton.addActionListener((ActionEvent ae) -> {
            AppRunner.changeScreen(cards, jfrm.getContentPane(), "Start");
        });
        // Set up GridBagConstaints.
        JLabel invisibleLabel = new JLabel(); // As placeholders for the grid.
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, .4, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        deathPane.add(invisibleLabel, c);
        invisibleLabel = new JLabel();
        // Add actual components.
        c = new GridBagConstraints(1, 1, 1, 1, .2, .7, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        deathPane.add(deathLabel, c);
        c = new GridBagConstraints(2, 2, 1, 1, .2, .1, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,10), 0, 15);
        deathPane.add(returnButton, c);

        return deathPane;
    }

    protected static void enableComponents(Container cont, boolean enable) {
        for (Component comp : cont.getComponents()) {
            comp.setEnabled(enable);
            if (comp instanceof Container)
                enableComponents((Container) comp, enable);
        }
    }

    private static void updateInventory(JList<String> itemList, Player player) {
        java.util.List<Item> items = player.getInventory();
        DefaultListModel<String> itemModel = new DefaultListModel<>();
        for (Item item : items)
            itemModel.addElement(item.getName());
        itemList.setModel(itemModel);
    }

    private static void updateActionLog(JTextArea actionTextArea, Usable usable, int result, Entity user) {
        // Add the action to the action log.
        StringBuilder actionResult =  new StringBuilder(user.getName() + " used " + usable.getName() + ".\n");
        if (result == -1)
            actionResult.append("The attack missed!\n");
        else if (result == 0)
            actionResult.append("It was not very effective.\n");
        else if (result == 2)
            actionResult.append("It was very effective!\n");
        actionTextArea.setText(actionTextArea.getText() + actionResult.toString());
    }
}