package comte.ui.controller;

import comte.ui.model.GameModel;
import comte.ui.view.StartMenuView;

/**
 * Controller for start menu screen
 * <p>
 * Handles game mode selection and create players based on selected mode
 * </p>
 */
public class StartMenuController implements Controller {

    private final GameModel model;
    private final StartMenuView view;

    /**
     * Construct a new start menu controller associated to a model
     *
     * @param model model manipulated by the controller
     */
    public StartMenuController(GameModel model) {
        this.model = model;
        view = new StartMenuView(this);
    }

    /**
     * Launch a new solo game : Human versus AI
     */
    public void newSoloGame() {
        view.close();
        Controller gameController = new GameController(model);
        model.addNewPlayer(true);
        model.addNewPlayer(false);
        gameController.displayView();
    }

    /**
     * Launch a new AI game : AI versus AI
     */
    public void newAIGame() {
        view.close();
        Controller gameController = new GameController(model);
        model.addNewPlayer(false);
        model.addNewPlayer(false);
        gameController.displayView();
    }

    @Override
    public void displayView() {
        view.display();
    }
}
