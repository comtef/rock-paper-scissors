package comte.ui.controller;

import comte.shapes.Shape;
import comte.ui.model.GameModel;
import comte.ui.view.GameView;

/**
 * Controller for main game screen
 * <p>
 * Updates model based on users actions on view
 * </p>
 * <p>
 * Handles move selection, next turn and end of game action
 * </p>
 */
public class GameController implements Controller {

    private final GameModel model;
    private final GameView view;

    /**
     * Construct a new game controller associated to a model
     *
     * @param model model manipulated by the controller
     */
    public GameController(GameModel model) {
        this.model = model;
        this.view = new GameView(this);
        model.addTurnResultListener(view);
        model.addNewTurnListener(view);
        model.addHumanMoveSelectionListener(view);
        model.addNewPlayerListener(view);
    }

    @Override
    public void displayView() {
        view.display();
    }

    /**
     * Update the model with a move recorded for the first player
     *
     * @param shape move made by the first player
     */
    public void firstPlayerMove(Shape shape) {
        model.setFirstPlayerMove(shape);
    }

    /**
     * Update the model with a move recorded for the second player
     *
     * @param shape move made by the second player
     */
    public void secondPlayerMove(Shape shape) {
        model.setSecondPlayerMove(shape);
    }

    /**
     * Update the model for the beginning of a new turn
     */
    public void nextTurn() {
        model.nextTurn();
    }

    /**
     * Close the game screen and return to the main menu
     */
    public void returnToStartMenu() {
        view.close();
        StartMenuController startMenuController = new StartMenuController(new GameModel());
        startMenuController.displayView();
    }
}
