package comte.ui.view;

/**
 * Interface for components used to record game move inputs
 * <p>
 * Components have to be able to be notified when to record input and reset their state between turns
 * </p>
 */
interface MoveInput {

    /**
     * Notify the component to begin input recording
     */
    void startInputRecording();

    /**
     * Reset component state for a next game turn
     */
    void reset();
}
