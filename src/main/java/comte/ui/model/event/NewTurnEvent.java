package comte.ui.model.event;

import java.util.EventObject;

/**
 * Indicates the beginning of a new game turn
 */
public class NewTurnEvent extends EventObject {

    private final int turn;
    private final boolean lastTurn;

    /**
     * Event indicating the beginning of a new turn
     *
     * @param source   source of the event
     * @param turn     new turn number
     * @param lastTurn indicate if this is the last turn of the game
     */
    public NewTurnEvent(Object source, int turn, boolean lastTurn) {
        super(source);
        this.turn = turn;
        this.lastTurn = lastTurn;
    }


    public int getTurn() {
        return turn;
    }

    public boolean isLastTurn() {
        return lastTurn;
    }
}
