import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaneBuilder {
    private static final Font NORMAL_FONT = new Font("Serif", Font.PLAIN, 30);
    private static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 55);
    
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
        c = new GridBagConstraints(0, 0, 3, 3, .4, .3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        
        // Set up the background.
        try {
            BufferedImage startBackground;
            startBackground = ImageIO.read(new File("Backgrounds/start_background.png"));
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
}