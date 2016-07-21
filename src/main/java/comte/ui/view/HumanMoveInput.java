package comte.ui.view;

import comte.shapes.Shapes;
import comte.ui.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Input recorder for human player moves
 */
class HumanMoveInput extends JPanel implements MoveInput {

    private final List<JButton> buttons = new ArrayList<>();

    /**
     * New input recorder for human player
     *
     * @param controller move selection will be notified to this controller
     */
    HumanMoveInput(GameController controller) {
        super(new FlowLayout());
        Shapes.ALL_SHAPES.stream().forEach(shape -> {
            JButton button = new JButton(shape.getType().toString());
            buttons.add(button);
            button.setBackground(Color.GRAY);
            button.addActionListener(event -> {
                controller.firstPlayerMove(shape);
                updateButtonsAfterSelection(button);
            });
            add(button);
        });
    }

    /**
     * Update buttons to disable input and highlight player selection
     *
     * @param selectedButton user selection
     */
    private void updateButtonsAfterSelection(JButton selectedButton) {
        buttons.stream().forEach(button -> {
            if (button.equals(selectedButton)) {
                button.setBackground(Color.GREEN);
            }
            button.setEnabled(false);
        });
    }

    @Override
    public void startInputRecording() {
        // Nothing to do
    }

    @Override
    public void reset() {
        buttons.stream().forEach(button -> {
            button.setEnabled(true);
            button.setBackground(Color.GRAY);
        });
    }
}
