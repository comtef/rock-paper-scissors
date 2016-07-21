package comte.ui.model.event;

import java.util.EventListener;

/**
 * Listener for turn results
 */
public interface TurnResultListener extends EventListener {

    /**
     * Notify of a new turn result
     *
     * @param event turn information
     */
    void onTurnResult(TurnResultEvent event);

}
