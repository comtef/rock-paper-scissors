package comte.ui.view;

import comte.ui.controller.StartMenuController;
import comte.ui.model.GameParameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Start menu of the game, display game logo and game mode selection
 * <p>
 * Solo player game : human player versus AI player
 * </p>
 * <p>
 * AI game : AI player versus AI player
 * </p>
 */
public class StartMenuView extends JFrame implements View {

    private final StartMenuController controller;

    /**
     * Construct a new start menu view
     *
     * @param controller controller used to notify user actions
     */
    public StartMenuView(StartMenuController controller) {
        this.controller = controller;

        setTitle("RockPaperScissors");
        setSize(GameParameters.SCREEN_WIDTH, GameParameters.SCREEN_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // align to center.
        setResizable(false);

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        try {
            BufferedImage myPicture = ImageIO.read(this.getClass().getClassLoader().getResource("assets/mainLogo.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.gridy = 0;
            add(picLabel, gridBagConstraints);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton startSoloPlayerGame = new JButton("Start player vs computer game");
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(startSoloPlayerGame, gridBagConstraints);
        startSoloPlayerGame.addActionListener(event -> this.controller.newSoloGame());

        JButton startAIGame = new JButton("Start computer vs computer game");
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(startAIGame, gridBagConstraints);
        startAIGame.addActionListener(event -> this.controller.newAIGame());
    }

    @Override
    public void display() {
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }
}
