package comte.ui.model.event;

import comte.ui.model.TurnResult;

import java.util.EventObject;

/**
 * Turn ended with @see {@link comte.ui.model.TurnResult}
 */
public class TurnResultEvent extends EventObject {

    private final TurnResult turnResult;
    private final int firstPlayerScore;
    private final int secondPlayerScore;

    /**
     * New turn result event
     *
     * @param source            source of the event
     * @param turnResult        turn result
     * @param firstPlayerScore  updated score of the first player
     * @param secondPlayerScore updated score of the second player
     */
    public TurnResultEvent(Object source, TurnResult turnResult, int firstPlayerScore, int secondPlayerScore) {
        super(source);
        this.turnResult = turnResult;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    /**
     * Return the result of the current turn
     *
     * @return the {@link TurnResult} for current turn
     */
    public TurnResult getTurnResult() {
        return turnResult;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }
}
