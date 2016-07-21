package comte.ui.view;

import comte.ui.controller.GameController;
import comte.ui.model.GameParameters;
import comte.ui.model.event.*;

import javax.swing.*;
import java.awt.*;

/**
 * Main game screen, displays game information and players sections
 * <p>
 * <p>
 * A header will display expected player actions (play a move, go to next turn, end game)
 * </p>
 * <p>
 * A score panel will display the current game score
 * </p>
 * <p>
 * The main part of the screen will display each player move selection UI
 * </p>
 * <p>
 * A footer section will display turn result
 * </p>
 */
public class GameView extends JFrame implements View, TurnResultListener, NewTurnListener, HumanMoveSelectionListener, NewPlayerListener {

    private final GameController controller;

    // Score labels
    private JLabel firstPlayerScoreLabel;
    private JLabel secondPlayerScoreLabel;

    // Ask for player action
    private final JLabel inviteText = new JLabel("Choose your move");

    // Turn number and actions
    private final JLabel turnLabel = new JLabel("Turn 1/" + GameParameters.TURN_COUNT);
    private final JButton nextTurn = new JButton("Next turn");
    private final JButton returnToStartMenu = new JButton("Return to start menu");

    // Turn result
    private final JLabel firstPlayerTurnResultLabel = new JLabel();
    private final JLabel secondPlayerTurnResultLabel = new JLabel();

    // Is the first player human or is it a AI only game?
    private boolean firstPlayerHuman = false;

    // First player move recorder
    private MoveInput firstPlayerMoveInput;

    // Second player move recorder
    private MoveInput secondPlayerMoveInput;

    // Indicates if we're playing the last turn
    private boolean lastTurn = false;

    /**
     * Construct a new game view
     *
     * @param controller controller used to notify user actions
     */
    public GameView(GameController controller) {
        this.controller = controller;

        setTitle("RockPaperScissors");
        setSize(GameParameters.SCREEN_WIDTH, GameParameters.SCREEN_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // align to center.
        setResizable(false);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));
        JPanel invitePanel = new JPanel();
        invitePanel.add(inviteText);
        headerPanel.add(Box.createVerticalStrut(50));
        headerPanel.add(invitePanel);
        headerPanel.add(Box.createVerticalStrut(50));

        add(headerPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(turnLabel);
        bottomPanel.add(nextTurn);
        nextTurn.addActionListener(event -> this.controller.nextTurn());
        nextTurn.setVisible(false);
        bottomPanel.add(returnToStartMenu);
        returnToStartMenu.setVisible(false);
        returnToStartMenu.addActionListener(event -> this.controller.returnToStartMenu());
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void display() {
        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 0, 50));
        JPanel firstPlayerScorePanel = new JPanel(new FlowLayout());
        firstPlayerScorePanel.add(new JLabel("First player : "));
        firstPlayerScoreLabel = new JLabel("0");
        firstPlayerScorePanel.add(firstPlayerScoreLabel);
        centerPanel.add(firstPlayerScorePanel);

        JPanel secondPlayerScorePanel = new JPanel(new FlowLayout());
        secondPlayerScorePanel.add(new JLabel("Second player : "));
        secondPlayerScoreLabel = new JLabel("0");
        secondPlayerScorePanel.add(secondPlayerScoreLabel);
        centerPanel.add(secondPlayerScorePanel);

        AIMoveInput secondPlayerInput = new AIMoveInput(this.controller, 2);
        this.secondPlayerMoveInput = secondPlayerInput;

        if (firstPlayerHuman) {
            HumanMoveInput firstPlayerInput = new HumanMoveInput(this.controller);
            this.firstPlayerMoveInput = firstPlayerInput;
            centerPanel.add(firstPlayerInput);
        } else {
            AIMoveInput firstPlayerInput = new AIMoveInput(this.controller, 1);
            this.firstPlayerMoveInput = firstPlayerInput;
            centerPanel.add(firstPlayerInput);

            firstPlayerInput.startInputRecording();
            secondPlayerInput.startInputRecording();
        }
        centerPanel.add(secondPlayerInput);

        JPanel firstPlayerTurnResult = new JPanel(new FlowLayout());
        firstPlayerTurnResult.add(firstPlayerTurnResultLabel);
        centerPanel.add(firstPlayerTurnResult);
        JPanel secondPlayerTurnResult = new JPanel(new FlowLayout());
        secondPlayerTurnResult.add(secondPlayerTurnResultLabel);
        centerPanel.add(secondPlayerTurnResult);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }

    @Override
    public void onTurnResult(TurnResultEvent event) {
        int firstPlayerScore = event.getFirstPlayerScore();
        firstPlayerScoreLabel.setText(String.valueOf(firstPlayerScore));
        int secondPlayerScore = event.getSecondPlayerScore();
        secondPlayerScoreLabel.setText(String.valueOf(secondPlayerScore));
        switch (event.getTurnResult()) {
            case FIRST_PLAYER_WIN:
                firstPlayerTurnResultLabel.setText("WIN");
                firstPlayerTurnResultLabel.setForeground(Color.GREEN);
                secondPlayerTurnResultLabel.setText("LOOSE");
                secondPlayerTurnResultLabel.setForeground(Color.RED);
                break;
            case SECOND_PLAYER_WIN:
                firstPlayerTurnResultLabel.setText("LOOSE");
                firstPlayerTurnResultLabel.setForeground(Color.RED);
                secondPlayerTurnResultLabel.setText("WIN");
                secondPlayerTurnResultLabel.setForeground(Color.GREEN);
                break;
            case TIE:
                firstPlayerTurnResultLabel.setText("TIE");
                firstPlayerTurnResultLabel.setForeground(Color.BLACK);
                secondPlayerTurnResultLabel.setText("TIE");
                secondPlayerTurnResultLabel.setForeground(Color.BLACK);
                break;
        }

        if (lastTurn) {
            nextTurn.setVisible(false);
            returnToStartMenu.setVisible(true);
            if (firstPlayerScore > secondPlayerScore) {
                inviteText.setText("First player win");
            } else if (secondPlayerScore > firstPlayerScore) {
                inviteText.setText("Second player win");
            } else {
                inviteText.setText("Draw");
            }
        } else {
            inviteText.setText("Play next turn");
            nextTurn.setVisible(true);
        }
    }

    @Override
    public void onNewTurn(NewTurnEvent event) {
        turnLabel.setText("Turn " + event.getTurn() + "/" + GameParameters.TURN_COUNT);
        firstPlayerTurnResultLabel.setText("");
        secondPlayerTurnResultLabel.setText("");
        nextTurn.setVisible(false);

        firstPlayerMoveInput.reset();
        secondPlayerMoveInput.reset();

        inviteText.setText("Choose your move");

        if (!firstPlayerHuman) {
            firstPlayerMoveInput.startInputRecording();
            secondPlayerMoveInput.startInputRecording();
        }

        lastTurn = event.isLastTurn();
    }

    @Override
    public void onHumanMoveSelection(HumanMoveSelectionEvent event) {
        secondPlayerMoveInput.startInputRecording();
    }

    @Override
    public void onNewPlayer(NewPlayerEvent event) {
        if (event.isHuman()) {
            firstPlayerHuman = true;
        }
    }
}
