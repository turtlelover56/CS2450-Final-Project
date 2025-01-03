import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class PaneBuilder  {
	// Fonts & Color
	private static final Font NORMAL_FONT = new Font("Serif", Font.PLAIN, 30);
	private static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 55);
	private static final Color HEALTH_RED = new Color(255, 100, 100);

	private static JList<String> itemList;
	
	/** Builds the Starting Menu Panel
	 * 
	 * @param jfrm	the JFrame (needed for action listeners to work)
	 * @param cards	the CardLayout
	 * 
	 * @return	the GUI/panel of the start screen
	 */
	static JPanel buildStartPanel(JFrame jfrm, CardLayout cards) {

		// Components - 1 Main Panel, 1 Label, 1 Button Panel (3 Buttons)
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
		startButton.setBackground(Color.WHITE);

		JButton statsButton = new JButton("Battle Stats");
		statsButton.setFont(NORMAL_FONT);
		statsButton.setBackground(Color.WHITE);

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(NORMAL_FONT);
		exitButton.setBackground(Color.WHITE);

		// Add the buttons to the buttonPanel
		buttonPanel.add(startButton);
		buttonPanel.add(statsButton);
		buttonPanel.add(exitButton);

		// Set up GridBagConstraints
		JLabel invisibleLabel = new JLabel(); // As placeholders for the grid
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
		
		// Setting Up Background
		try {
			c = new GridBagConstraints(0, 0, 3, 3, .4, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
			BufferedImage startBackground;
			startBackground = ImageIO.read(new File("Backgrounds/Start_Background.png"));
			JLabel picLabel = new JLabel(new ImageIcon(startBackground));
			startPane.add(picLabel, c);
		} catch (IOException ex) {
			System.out.println("Unable to load background :(");
		}


		// Action Listeners
		startButton.addActionListener((ActionEvent ae) -> {
			// Changes the screen to the battle screen.
			AppRunner.changeScreen(cards, jfrm.getContentPane(), "Battle");
		});

		statsButton.addActionListener((ActionEvent ae) -> {
			// Changes the screen to the statistics screen.
			showStatusPanel();
		});

		exitButton.addActionListener((ActionEvent ae) -> {
			// Close the frame and exit the program.
			jfrm.dispose();
		});

		return startPane;
	}

	// Shows the stats of the player (unless it's their first time playing, see below).
	static void showStatusPanel() {
        StringBuilder statistics = new StringBuilder();
        try {
            // Read the statistics from the file
            BufferedReader reader = new BufferedReader(new FileReader("Statistics.txt"));
            String line;
            while ((line = reader.readLine()) != null) { 
                statistics.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            statistics.append("No statistics file yet. Play the game!"); // The game will make a .txt once you win or lose a fight!
        }
        JOptionPane.showMessageDialog(null, statistics.toString(), "Battle Statistics", JOptionPane.INFORMATION_MESSAGE);
    }

	/** Builds the Battle Screen Panel
	 * 
	 * @param jfrm	the JFrame
	 * @param cards	the CardLayout
	 * @param encounter	the player and the enemy
	 * 
	 * @param currentBattlePane
	 * 
	 * @return	the GUI/panel of the battle screen
	 */
	static JPanel buildBattlePanel(JFrame jfrm, CardLayout cards, Encounter encounter) {


		Player player = encounter.getPlayer();
		Enemy enemy = encounter.getEnemy();

		// Components - 1 Main Panel, 1 TextArea (Scrollable)
		JPanel battlePane = new JPanel();
		battlePane.setLayout(new GridBagLayout());

		JTextArea actionTextArea = new JTextArea();
		actionTextArea.setEditable(false);
		actionTextArea.setLineWrap(true);
		JScrollPane actionScrollPane = new JScrollPane(actionTextArea);
		actionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		// Components (Player) - 1 Label, 1 ProgressBar, 1 Picture Icon
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


		// Components (Enemy) - 1 Label, 1 ProgressBar, 1 Picture Icon
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


		// Defining (Player) Attack JList (Wrapped In JScrollPane) Component w/ ListSelectionListener
		List<Attack> attacks = player.getSelectedAttacks();
		DefaultListModel<String> attackModel = new DefaultListModel<>();
		for (Attack attack : attacks)
			attackModel.addElement(attack.getName());
		JList<String> attackList = new JList<>(attackModel);

		attackList.addListSelectionListener((ListSelectionEvent lse) -> {
			if (!attackList.isSelectionEmpty()) {
				String attackName = attackList.getSelectedValue();
				int result;

				// Calculate damage from selected attack and update action log
				for (Attack attack : player.getSelectedAttacks()) {
					if (attack.getName().equals(attackName)) {
						result = encounter.resolveAttack(attack, !attack.isTargetUser());
						updateActionLog(actionTextArea, attack, result, player);
					}
				}

				// Update health bars
				playerHealth.setValue(player.getCurrentHealth());
				enemyHealth.setValue(enemy.getCurrentHealth());

				// Check to see if the enemy is dead; if true, show intermission screen
				if (enemyHealth.getValue() <= 0) {
					AppRunner.changeScreen(cards, jfrm.getContentPane(), "Intermission");
					AppRunner.setUpNewBattle();
					AppRunner.getBattleStats().win();
				}

				attackList.clearSelection();
		  
				// Run the Enemy's turn and update action log
				Attack enemyAttack = enemy.attack();
				result = encounter.resolveAttack(enemyAttack, enemyAttack.isTargetUser());
				updateActionLog(actionTextArea, enemyAttack, result, enemy);

				// Update health bars
				playerHealth.setValue(player.getCurrentHealth());
				enemyHealth.setValue(enemy.getCurrentHealth());

				// Check to see if the player is dead; if true, show death screen
				if (playerHealth.getValue() <= 0) {
					AppRunner.changeScreen(cards, jfrm.getContentPane(), "Death");
					AppRunner.createPlayer();
					AppRunner.setUpNewBattle();
					AppRunner.getBattleStats().lose();
				}
			}
		});
		JScrollPane attackScrollPane = new JScrollPane(attackList);
		

		// Defining (Player) Item JList (Wrapped In JScrollPane) Component w/ ListSelectionListener
		itemList = new JList<>();
		updateInventory(itemList, player);

		itemList.addListSelectionListener((ListSelectionEvent lse) -> {
			if (!itemList.isSelectionEmpty()) {
				String itemName = itemList.getSelectedValue();
				int result;

				// Calculate damage/health from selected item and update inventory & action log
				for (Item item : player.getInventory())
					if (item.getName().equals(itemName)) {
						result = encounter.resolveItem(item, !item.isTargetUser());
						updateInventory(itemList, player);
						updateActionLog(actionTextArea, item, result, player);
						break;
					}

				// Update health bars
				playerHealth.setValue(player.getCurrentHealth());
				enemyHealth.setValue(enemy.getCurrentHealth());

				// Check to see if the enemy is dead; if true, show intermission screen
				if (enemyHealth.getValue() <= 0) {
					AppRunner.changeScreen(cards, jfrm.getContentPane(), "Intermission");
					AppRunner.setUpNewBattle();
					AppRunner.getBattleStats().win();
				}

				itemList.clearSelection();

				// Run the Enemy's turn and update action log
				Attack enemyAttack = enemy.attack();
				result = encounter.resolveAttack(enemyAttack, enemyAttack.isTargetUser());
				updateActionLog(actionTextArea, enemyAttack, result, enemy);

				// Update health bars.
				playerHealth.setValue(player.getCurrentHealth());
				enemyHealth.setValue(enemy.getCurrentHealth());

				// Check to see if the player is dead; if true, show death screen
				if (playerHealth.getValue() <= 0) {
					AppRunner.changeScreen(cards, jfrm.getContentPane(), "Death");
					AppRunner.createPlayer();
					AppRunner.setUpNewBattle();
					AppRunner.getBattleStats().lose();
				}
			}
		});
		
		JScrollPane itemScrollPane = new JScrollPane(itemList);


		// Components - 1 Button Panel (3 Buttons) w/ ActionListeners
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,1, 0, 10));
		buttonPanel.setOpaque(false);

		JButton attackButton = new JButton("Attack");
		attackButton.setFont(NORMAL_FONT);
		attackButton.setForeground(Color.BLACK);
		attackButton.setBackground(Color.PINK);

		JButton itemsButton = new JButton("Items");
		itemsButton.setFont(NORMAL_FONT);
		itemsButton.setForeground(Color.BLACK);
		itemsButton.setBackground(Color.ORANGE);

		JButton runButton = new JButton("Run");
		runButton.setFont(NORMAL_FONT);
		runButton.setForeground(Color.BLACK);
		runButton.setBackground(Color.CYAN);
		
		attackButton.addActionListener((ActionEvent ae) -> {
			itemScrollPane.setVisible(false);
			attackScrollPane.setVisible(!attackScrollPane.isVisible());
		});

		itemsButton.addActionListener((ActionEvent ae) -> {
			attackScrollPane.setVisible(false);
			itemScrollPane.setVisible(!itemScrollPane.isVisible());
		});
		
		runButton.addActionListener((ActionEvent ae) -> {
			AppRunner.changeScreen(cards, jfrm.getContentPane(), "Start");
			AppRunner.setUpNewBattle();
		});


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


	/** Builds the Intermission Screen Panel
	 * 
	 * @param jfrm	the JFrame
	 * @param cards	the CardLayout
	 * @param intermission	the Intermission prompt
	 * @param player	the player
	 * 
	 * 
	 * 
	 * @return	the GUI/panel of the intermission screen
	 */
	static JPanel buildIntermissionPanel(JFrame jfrm, CardLayout cards, List<Intermission> intermissionDex, Player player) {
		// Pick a random intermission event.
		Intermission intermission = intermissionDex.get((int) (Math.random() * intermissionDex.size()));

		// Components - 1 Main Panel, 1 Label, 1 Button Panel (2 buttons), 1 Button
		JPanel intermissionPane = new JPanel(new GridBagLayout());
		
		JLabel intermissionLabel = new JLabel("<html>" + intermission.getPrompt());
		intermissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		intermissionLabel.setBorder(BorderFactory.createEtchedBorder());
		intermissionLabel.setOpaque(true);
		intermissionLabel.setMinimumSize(new Dimension(800, 300));
		
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		buttonPanel.setOpaque(false);

		JButton optionOneButton = new JButton(intermission.getOptionOne());
		optionOneButton.setFont(NORMAL_FONT);
		optionOneButton.setBackground(new Color(186,225,255));

		JButton optionTwoButton = new JButton(intermission.getOptionTwo());
		optionTwoButton.setFont(NORMAL_FONT);
		optionTwoButton.setBackground(new Color(186,225,255));

		JButton nextButton = new JButton("Next Encounter");
		nextButton.setFont(NORMAL_FONT);
		nextButton.setBackground(new Color(255, 255, 186));
		nextButton.setVisible(false);

		// Add buttons to buttonPanel
		buttonPanel.add(optionOneButton);
		buttonPanel.add(optionTwoButton);
		buttonPanel.add(nextButton);

		// Action Listeners
		optionOneButton.addActionListener((ActionEvent ae) -> {
			intermissionLabel.setText("<html>" + intermission.getResultOne());
			optionOneButton.setVisible(false);
			optionTwoButton.setVisible(false);
			nextButton.setVisible(true);
			player.addItem(intermission.getOptionOneItem());
			updateInventory(itemList, player);
		});

		optionTwoButton.addActionListener((ActionEvent ae) -> {
			intermissionLabel.setText("<html>" + intermission.getResultTwo());
			optionOneButton.setVisible(false);
			optionTwoButton.setVisible(false);
			nextButton.setVisible(true);
			player.addItem(intermission.getOptionTwoItem());
			updateInventory(itemList, player);
		});


		nextButton.addActionListener((ActionEvent ae) -> {
			//Change to the "Battle" screen (card)
			AppRunner.changeScreen(cards, jfrm.getContentPane(), "Battle");
			// Switch to the new intermission pane.
			AppRunner.addPane(buildIntermissionPanel(jfrm, cards, intermissionDex, player), "Intermission");
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

	/** Builds the Death Screen Panel 
	 * 
	 * @param jfrm	the JFrame
	 * @param cards	the CardLayout
	 * 
	 * @return	the GUI/panel of the death screen
	 */
	static JPanel buildDeathPanel(JFrame jfrm, CardLayout cards) {

		// Components - 1 Main Panel, 1 Label, 1 Button
		JPanel deathPane = new JPanel(new GridBagLayout());
		deathPane.setBackground(Color.BLACK);

		JLabel deathLabel = new JLabel("You died.");
		deathLabel.setFont(TITLE_FONT);
		deathLabel.setForeground(Color.RED);

		JButton returnButton = new JButton("Return to Menu?");
		returnButton.setFont(NORMAL_FONT);
		returnButton.setForeground(Color.WHITE);
		returnButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		returnButton.setBackground(Color.BLACK);

		//Bold on hover
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				returnButton.setFont(returnButton.getFont().deriveFont(Font.BOLD));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				returnButton.setFont(NORMAL_FONT);
			}
		});

		// Action Listener
		returnButton.addActionListener((ActionEvent ae) -> {
			AppRunner.changeScreen(cards, jfrm.getContentPane(), "Start");
		});


		// Set up GridBagConstraints.
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
		List<Item> items = player.getInventory();
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
