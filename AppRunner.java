/*
    The Swing implementation and main method for PokéPath.
*/
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppRunner {
    AppRunner() {
        // Open game data file.
        boolean ableToRun = true;
        Scanner sc = null;
        try {
            sc = new Scanner(new File("Game Data.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Game Data file not found :(");
            ableToRun = false;
        }
        if (ableToRun) {
            String section = null;
            java.util.List<Attack> attackDex = new ArrayList<>();
            java.util.List<Usable> itemDex = new ArrayList<>();
            java.util.List<Entity> entityDex = new ArrayList<>();
            java.util.List<Intermission> intermissionDex = new ArrayList<>();
            // Read game data.
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\t");
                if (line.length == 1)
                    section = line[0];
                else {
                    switch (section) {
                        // For some reason the first line of the csv reads as this???
                        case "Attack":
                            attackDex.add(new Attack(Integer.parseInt(line[0]), new Usable(line[1], Boolean.parseBoolean(line[2]), Boolean.parseBoolean(line[3]), new Effect(Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]), Boolean.parseBoolean(line[7])))));
                            break;
                        case "Item":
                            itemDex.add(new Usable(line[0], Boolean.parseBoolean(line[1]), Boolean.parseBoolean(line[2]), new Effect(Integer.parseInt(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]), Boolean.parseBoolean(line[6]))));
                            break;
                        case "Entity":
                            int element;
                            java.util.List<Attack> entityAttackList = new ArrayList<>();
                            if (line[0].equals("Player")) {
                                // Randomize the player's element and give them a corresponding attack.
                                element = (int) (Math.random() * 4);
                                String searchAttackName = null;
                                switch (element) {
                                    case 0:
                                        searchAttackName = "Fire Breath";
                                        break;
                                    case 1:
                                        searchAttackName = "Water Ball";
                                        break;
                                    case 2:
                                        searchAttackName = "Lightning Strike";
                                        break;
                                    case 3:
                                        searchAttackName = "Wood Thwack";
                                        break;
                                    default:
                                        System.out.println("Error: Element " + element + " not found.");
                                }
                                for (Attack attack : attackDex) {
                                    if (attack.getName().equals(searchAttackName)) {
                                        entityAttackList.add(attack);
                                        break;
                                    }
                                }
                            }
                            else
                                element = Integer.parseInt(line[2]);
                            Entity entity = new Entity(line[0], Integer.parseInt(line[1]), element);
                            for (int i = 3; i < line.length - 2; i++) {
                                for (int j = 0; j < attackDex.size(); j++) {
                                    Attack selectedAttack = attackDex.get(j);
                                    if (line[i].equals(selectedAttack.getName())) {
                                        entityAttackList.add(selectedAttack);
                                        j = attackDex.size(); // Bad Practice :(
                                    }
                                }
                            }
                            entity.setAttacks(entityAttackList);
                            entity.setDefaultPose(new ImageIcon(line[line.length-2]));
                            entity.setAttackPose(new ImageIcon(line[line.length-1]));
                            entityDex.add(entity);
                            break;
                        case "Intermission":
                            Usable itemOne = null;
                            Usable itemTwo = null;
                            int index = 0;
                            while ((itemOne == null || itemTwo == null) && index < itemDex.size()) {
                                Usable itemFound = itemDex.get(index);
                                if (line[3].equals(itemFound.getName()))
                                    itemOne = itemFound;
                                else if (line[4].equals(itemFound.getName()))
                                    itemTwo = itemFound;
                                index++;
                            }
                            intermissionDex.add(new Intermission(line[0], line[1], line[2], new Item(itemOne, Integer.parseInt(line[5])), new Item(itemTwo, Integer.parseInt(line[6])), line[7], line[8]));
                            break;
                        default:
                            System.out.println("Error: Section " + section + " not found.");
                    }
                }
            }
            // Debugging prints.
            System.out.println(attackDex);
            System.out.println(itemDex);
            System.out.println(entityDex);  
            System.out.println(intermissionDex);            

            
            // Create the frame.
            JFrame jfrm = new JFrame("PokéPath! Definitely not copyrighted...");
            jfrm.setSize(1500, 800);
            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jfrm.setResizable(false);
            CardLayout cards = new CardLayout();
            jfrm.setLayout(cards);

            // Build the starting menu.
            JPanel startPane = PaneBuilder.buildStartPanel(jfrm, cards);
            // Build the first battle menu.
            // Create the player.
            Player player = null;
            for (Entity entity : entityDex) {
                if (entity.getName().equals("Player")) {
                    player = new Player(entity);
                    break; // Bad Practice :(
                }
            }
            for (Usable item : itemDex) {
                if (item.getName().equals("Health Potion") || item.getName().equals("Fire Bottle"))
                    player.addItem(new Item(item, 3));
            }
            Enemy enemy = null;
            while (enemy == null) {
                int roll = (int) (Math.random() * entityDex.size());
                Entity chosenEntity = entityDex.get(roll);
                if (!chosenEntity.getName().equals("Player"))
                    enemy = new Enemy(chosenEntity);
            }
            Encounter encounter = new Encounter(enemy, player);
            JPanel battlePane = PaneBuilder.buildBattlePanel(jfrm, cards, encounter);
            encounter.setBattlePane(battlePane);
            // Build the intermission screen.
            Intermission intermission = intermissionDex.get((int) (Math.random() * intermissionDex.size()));
            JPanel intermissionPane = PaneBuilder.buildIntermissionPanel(jfrm, cards, intermission, player);

            // Build the death screen.
            JPanel deathPane = PaneBuilder.buildDeathPanel(jfrm, cards);
            
            // Add the panes to the frame.
            jfrm.add(startPane, "Start");
            jfrm.add(battlePane, "Battle");
            jfrm.add(intermissionPane, "Intermission");
            jfrm.add(deathPane, "Death");

            // Debugging tool to change screens instantly.
            //changeScreen(cards, jfrm.getContentPane(), "Intermission");

            // Make the frame visible.
            jfrm.setVisible(true);
        }
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