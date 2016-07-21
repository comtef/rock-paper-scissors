package comte.ui.model.event;

import java.util.EventListener;

/**
 * Listener for new players
 */
public interface NewPlayerListener extends EventListener {

    /**
     * Notify of a new player
     *
     * @param event selection event
     */
    void onNewPlayer(NewPlayerEvent event);

}
