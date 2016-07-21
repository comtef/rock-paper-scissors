package comte.ui.model.event;

import java.util.EventObject;

/**
 * Indicates a new player, human or AI
 */
public class NewPlayerEvent extends EventObject {

    private final boolean human;

    /**
     * Event indicating a new player, human or AI
     *
     * @param source   source of the event
     * @param human    true if the player is human, false if its an AI
     */
    public NewPlayerEvent(Object source, boolean human) {
        super(source);
        this.human = human;
    }

    public boolean isHuman() {
        return human;
    }
}
