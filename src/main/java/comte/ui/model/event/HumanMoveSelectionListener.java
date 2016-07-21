package comte.ui.model.event;

import java.util.EventListener;

/**
 * Listener for human move selection
 */
public interface HumanMoveSelectionListener extends EventListener {

    /**
     * Notify of a move selection
     *
     * @param event selection event
     */
    void onHumanMoveSelection(HumanMoveSelectionEvent event);

}
