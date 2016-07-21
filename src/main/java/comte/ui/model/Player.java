package comte.ui.model;

import comte.shapes.Shape;

class Player {

    private boolean human = false;
    private int score = 0;
    private Shape move;

    public Player(boolean human) {
        this.human = human;
    }

    public boolean isHuman() {
        return human;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Shape getMove() {
        return move;
    }

    public void setMove(Shape move) {
        this.move = move;
    }
}
