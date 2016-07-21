package comte.ui.model;

import comte.shapes.Rock;
import comte.shapes.Scissors;
import comte.ui.model.event.TurnResultEvent;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;


public class GameModelTest implements WithAssertions {

    @Test
    public void bothPlayersPlayedWithNoStateInitialized() throws Exception {
        GameModel model = new GameModel();
        assertThatThrownBy(model::bothPlayersPlayed).isInstanceOf(AssertionError.class);
    }

    @Test
    public void bothPlayersPlayedWithNoMove() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);
        assertThat(model.bothPlayersPlayed()).isFalse();
    }

    @Test
    public void bothPlayersPlayedWithOnlyFirstPlayerMove() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);
        model.setFirstPlayerMove(new Rock());
        assertThat(model.bothPlayersPlayed()).isFalse();
    }

    @Test
    public void bothPlayersPlayedWithOnlySecondPlayerMove() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);
        model.setSecondPlayerMove(new Rock());
        assertThat(model.bothPlayersPlayed()).isFalse();
    }

    @Test
    public void bothPlayersPlayedWithBothPlayersMove() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);
        model.setFirstPlayerMove(new Rock());
        model.setSecondPlayerMove(new Rock());
        assertThat(model.bothPlayersPlayed()).isTrue();
    }

    @Test
    public void computeTurnResultFirstPlayerWin() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);

        // Add listener for turn result events
        final TurnResult[] turnResult = {null};
        model.addTurnResultListener((event) -> turnResult[0] = event.getTurnResult());

        model.setFirstPlayerMove(new Rock());
        model.setSecondPlayerMove(new Scissors());

        // Check event
        assertThat(turnResult[0]).isEqualTo(TurnResult.FIRST_PLAYER_WIN);
    }

    @Test
    public void computeTurnResultSecondPlayerWin() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);

        // Add listener for turn result events
        final TurnResultEvent[] turnResult = {null};
        model.addTurnResultListener((event) -> turnResult[0] = event);

        model.setSecondPlayerMove(new Rock());
        model.setFirstPlayerMove(new Scissors());

        // Check event
        assertThat(turnResult[0].getTurnResult()).isEqualTo(TurnResult.SECOND_PLAYER_WIN);
        assertThat(turnResult[0].getFirstPlayerScore()).isEqualTo(0);
        assertThat(turnResult[0].getSecondPlayerScore()).isEqualTo(1);
    }

    @Test
    public void computeTurnResultTie() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);

        // Add listener for turn result events
        final TurnResult[] turnResult = {null};
        model.addTurnResultListener((event) -> turnResult[0] = event.getTurnResult());

        model.setFirstPlayerMove(new Scissors());
        model.setSecondPlayerMove(new Scissors());

        // Check event
        assertThat(turnResult[0]).isEqualTo(TurnResult.TIE);
    }

    @Test
    public void nextTurnShouldResetState() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);

        // Add listener for new turn events
        final int[] turn = {0};
        model.addNewTurnListener((event) -> turn[0] = event.getTurn());

        model.nextTurn();

        // Check event
        assertThat(turn[0]).isEqualTo(2);
    }

    @Test
    public void nextTurnShouldNotifyOfLastTurn() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);

        // Add listener for new turn events
        final int[] turn = {0};
        final boolean[] lastTurn = {false};
        model.addNewTurnListener((event) -> {
            turn[0] = event.getTurn();
            lastTurn[0] = event.isLastTurn();
        });

        for (int i = 2; i < GameParameters.TURN_COUNT; i++) {
            model.nextTurn();
            assertThat(turn[0]).isEqualTo(i);
            assertThat(lastTurn[0]).isFalse();
        }

        // Check last event
        model.nextTurn();
        assertThat(turn[0]).isEqualTo(GameParameters.TURN_COUNT);
        assertThat(lastTurn[0]).isTrue();
    }

    @Test
    public void nextTurnCannotBeCalledAfterLastTurn() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);

        for (int i = 2; i <= GameParameters.TURN_COUNT; i++) {
            model.nextTurn();
        }

        assertThatThrownBy(model::nextTurn).isInstanceOf(AssertionError.class);
    }

    @Test
    public void addNewPlayerCannotAddMoreThanTwoPlayers() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(false);
        model.addNewPlayer(false);
        assertThatThrownBy(() -> model.addNewPlayer(false)).isInstanceOf(AssertionError.class);
    }

    @Test
    public void addNewPlayerFireEvent() throws Exception {
        GameModel model = new GameModel();

        // Add listener for new player events
        final boolean[] listenerCalled = {false};
        model.addNewPlayerListener(event -> listenerCalled[0] = true);

        model.addNewPlayer(true);

        // Check event
        assertThat(listenerCalled[0]).isTrue();
    }

    @Test
    public void setFirstPlayerMoveForHumanPlayerFireEvent() throws Exception {
        GameModel model = new GameModel();
        model.addNewPlayer(true);
        model.addNewPlayer(false);

        // Add listener for human move selection events
        final boolean[] listenerCalled = {false};
        model.addHumanMoveSelectionListener(event -> listenerCalled[0] = true);

        model.setFirstPlayerMove(new Scissors());

        // Check event
        assertThat(listenerCalled[0]).isTrue();
    }
}