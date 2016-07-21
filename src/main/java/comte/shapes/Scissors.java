package comte.shapes;

import javax.annotation.Nonnull;

/**
 * Scissors shape implementation
 */
public class Scissors implements Shape {

    @Override
    public ShapeType getType() {
        return ShapeType.SCISSORS;
    }

    @Override
    public boolean beats(@Nonnull Shape otherShape) {
        return otherShape.getType().equals(ShapeType.PAPER);
    }
}
