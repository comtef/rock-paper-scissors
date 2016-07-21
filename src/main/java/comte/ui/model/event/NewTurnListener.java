package comte.ui.model.event;

import java.util.EventListener;

/**
 * Listener for new turns
 */
public interface NewTurnListener extends EventListener {

    /**
     * Notify of a new turn
     *
     * @param event new turn information
     */
    void onNewTurn(NewTurnEvent event);

}
