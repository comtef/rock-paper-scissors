package comte.ui.model.event;

import java.util.EventObject;

/**
 * Indicates the selection of a move by a human player
 */
public class HumanMoveSelectionEvent extends EventObject {

    /**
     * Event indicating the selection of a move by a human player
     *
     * @param source source of the event
     */
    public HumanMoveSelectionEvent(Object source) {
        super(source);
    }
}
