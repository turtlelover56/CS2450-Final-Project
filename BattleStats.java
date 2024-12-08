import java.io.*;
import java.util.Scanner;

public class BattleStats {
    private int wins;
    private int losses;
    private double winRate;

    public BattleStats() {
        loadStats();
    }

    // Tries to get the stats from the file.
    // If the file doesn't exist, it sets everything to zero.
    /* (Which the player won't even see, since the PaneBuilder code
        gives an error message if there's no Statistics.txt file) */
    private void loadStats() {
        try (Scanner scan = new Scanner(new File("Statistics.txt"));) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (line.startsWith("Number of wins:")) {
                    wins = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Number of losses:")) {
                    losses = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.startsWith("Win rate:")) {
                    winRate = Double.parseDouble(line.split(":")[1].trim().replace("%", "")) / 100;
                }
            }
		} catch (FileNotFoundException e) {
			System.err.println("Statistics file not found, setting default values...");
            wins = 0;
            losses = 0;
            winRate = 0.0;
		}
    }

    // This should happen every time the player reaches the intermission screen.
    public void win() {
        wins++;
        winRate = ((double) wins/(wins + losses));
        saveStats();
    }

    // This should happen every time the player reaches the death screen.
    public void lose() {
        losses++;
        winRate = ((double) wins/(wins + losses));
        saveStats();
    }

    // Fun fact: This actually makes a Statistics.txt file if you don't have one already!
    // Also fun fact: I had to figure out what a FileWriter is while making this! -V
    public void saveStats() {
        try (FileWriter writer = new FileWriter("Statistics.txt")) {
            writer.write("Number of wins: " + wins + "\n");
            writer.write("Number of losses: " + losses + "\n");
            writer.write("Win rate: " + String.format("%.2f", winRate * 100) + "%\n");
        } catch (IOException e) {
            System.err.println("Could not update Statistics file. :(");
        }
    }
}
