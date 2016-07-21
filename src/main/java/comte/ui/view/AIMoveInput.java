package comte.ui.view;

import comte.shapes.Shape;
import comte.shapes.Shapes;
import comte.ui.controller.GameController;
import comte.ui.model.GameParameters;

import javax.swing.*;
import java.awt.*;

/**
 * Input recorder for AI moves
 * <p>
 * AI will choose a random move every turn
 * </p>
 */
class AIMoveInput extends JPanel implements MoveInput {

    private final JLabel aiStatus = new JLabel("Waiting for input");
    private final GameController controller;
    private final int playerId;

    /**
     * An AI move input recorder will generate moves for the corresponding player
     *
     * @param controller move selection will be notified to this controller
     * @param playerId   the player identifier for whom moves will be generated
     */
    AIMoveInput(GameController controller, int playerId) {
        super(new FlowLayout());
        this.controller = controller;
        this.playerId = playerId;
        add(aiStatus);
    }

    @Override
    public void startInputRecording() {
        aiStatus.setText("Computing move...");
        Timer timer = new Timer(GameParameters.AI_MOVE_LATENCY, event -> {
            Shape shape = Shapes.getRandomShape();
            aiStatus.setText("Selected " + shape.getType().toString());
            if (playerId == 1) {
                controller.firstPlayerMove(shape);
            } else {
                controller.secondPlayerMove(shape);

            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void reset() {
        aiStatus.setText("Waiting for input");
    }
}
