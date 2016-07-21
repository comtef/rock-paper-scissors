package comte;

import comte.ui.controller.StartMenuController;
import comte.ui.model.GameModel;

import javax.swing.*;
import java.io.IOException;

/**
 * Game runner, launch GUI
 */
class Runner {

    /**
     * Game main
     *
     * @param args program arguments
     */
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            StartMenuController controller = new StartMenuController(new GameModel());
            controller.displayView();
        });
    }
}
