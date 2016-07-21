package comte.ui.model;

import comte.shapes.Shape;
import comte.ui.model.event.*;

import javax.swing.event.EventListenerList;
import java.util.ArrayList;
import java.util.List;

/**
 * Application model (as in MVC pattern)
 * <p>
 * Holds game state content, update state based on controllers actions and notifies views when updated
 * </p>
 */
public class GameModel {

    private final EventListenerList listeners;
    private final List<Player> players = new ArrayList<>();
    private int turn = 1;

    /**
     * Current turn result
     */
    private TurnResult turnResult;

    /**
     * New game model with empty state
     */
    public GameModel() {
        listeners = new EventListenerList();
    }

    /**
     * Add new listener for end of turn updates
     *
     * @param listener listener to be notified
     */
    public void addTurnResultListener(TurnResultListener listener) {
        listeners.add(TurnResultListener.class, listener);
    }

    /**
     * Add new listener for beginning of new turn updates
     *
     * @param listener listener to be notified
     */
    public void addNewTurnListener(NewTurnListener listener) {
        listeners.add(NewTurnListener.class, listener);
    }

    /**
     * Add new listener for human move selection updates
     *
     * @param listener listener to be notified
     */
    public void addHumanMoveSelectionListener(HumanMoveSelectionListener listener) {
        listeners.add(HumanMoveSelectionListener.class, listener);
    }

    /**
     * Add new listener for new player updates
     *
     * @param listener listener to be notified
     */
    public void addNewPlayerListener(NewPlayerListener listener) {
        listeners.add(NewPlayerListener.class, listener);
    }

    /**
     * Notifies all listeners waiting for turn result updates
     */
    private void fireTurnResultEvent() {
        TurnResultListener[] turnResultListeners = this.listeners.getListeners(TurnResultListener.class);

        for (TurnResultListener listener : turnResultListeners) {
            listener.onTurnResult(new TurnResultEvent(this, turnResult, players.get(0).getScore(), players.get(1).getScore()));
        }
    }

    /**
     * Notifies all listeners waiting for new turn updates
     */
    private void fireNewTurnEvent(boolean lastTurn) {
        NewTurnListener[] newTurnListeners = this.listeners.getListeners(NewTurnListener.class);

        for (NewTurnListener listener : newTurnListeners) {
            listener.onNewTurn(new NewTurnEvent(this, turn, lastTurn));
        }
    }

    /**
     * Notifies all listeners waiting for human move selection updates
     */
    private void fireHumanMoveSelectionEvent() {
        HumanMoveSelectionListener[] humanMoveSelectionListeners = this.listeners.getListeners(HumanMoveSelectionListener.class);

        for (HumanMoveSelectionListener listener : humanMoveSelectionListeners) {
            listener.onHumanMoveSelection(new HumanMoveSelectionEvent(this));
        }
    }

    /**
     * Notifies all listeners waiting for new player updates
     */
    private void fireNewPlayerEvent(boolean human) {
        NewPlayerListener[] newPlayerListeners = this.listeners.getListeners(NewPlayerListener.class);

        for (NewPlayerListener listener : newPlayerListeners) {
            listener.onNewPlayer(new NewPlayerEvent(this, human));
        }
    }

    /**
     * Update model with first player move for current turn
     * <p>
     * Automatically compute turn result if both player moves are set
     * </p>
     * <p>
     * If the player is human, notify listeners of selection
     * </p>
     *
     * @param firstPlayerMove the move selected by the first player
     */
    public void setFirstPlayerMove(Shape firstPlayerMove) {
        players.get(0).setMove(firstPlayerMove);
        if (players.get(0).isHuman()) {
            fireHumanMoveSelectionEvent();
        } else if (bothPlayersPlayed()) {
            computeTurnResult();
        }
    }

    /**
     * Update model with second player move for current turn
     * <p>
     * Automatically compute turn result if both player moves are set
     * </p>
     *
     * @param secondPlayerMove the move selected by the second player
     */
    public void setSecondPlayerMove(Shape secondPlayerMove) {
        players.get(1).setMove(secondPlayerMove);
        if (bothPlayersPlayed()) {
            computeTurnResult();
        }
    }

    /**
     * Compute turn result based on moves selected by both players
     * <p>
     * Notify listeners of turn result
     * </p>
     */
    private void computeTurnResult() {
        if (players.get(0).getMove().beats(players.get(1).getMove())) {
            //first player wins
            players.get(0).setScore(players.get(0).getScore() + 1);
            turnResult = TurnResult.FIRST_PLAYER_WIN;
        } else if (players.get(1).getMove().beats(players.get(0).getMove())) {
            // second player wins
            players.get(1).setScore(players.get(1).getScore() + 1);
            turnResult = TurnResult.SECOND_PLAYER_WIN;
        } else {
            // draw
            turnResult = TurnResult.TIE;
        }
        fireTurnResultEvent();
    }

    /**
     * Indicates if a move is recorded for both players
     *
     * @return true if both moves are set, false otherwise
     */
    boolean bothPlayersPlayed() {
        assert players.size() == 2 : "Invalid state, two players should be initialized";
        return players.get(0).getMove() != null && players.get(1).getMove() != null;
    }

    /**
     * Update state for a new turn, reset moves and increments turn counter
     */
    public void nextTurn() {
        assert turn != GameParameters.TURN_COUNT : "Last turn already reached";
        turn++;
        players.get(0).setMove(null);
        players.get(1).setMove(null);
        fireNewTurnEvent(turn == GameParameters.TURN_COUNT);
    }

    /**
     * Initialize game state with a new player
     *
     * @param human is the player human or AI?
     */
    public void addNewPlayer(boolean human) {
        assert players.size() <= 1 : "Only 2 players can be added to the game";
        players.add(new Player(human));
        fireNewPlayerEvent(human);
    }
}
