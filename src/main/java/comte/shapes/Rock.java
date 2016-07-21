package comte.shapes;

import javax.annotation.Nonnull;

/**
 * Rock shape implementation
 */
public class Rock implements Shape {

    @Override
    public ShapeType getType() {
        return ShapeType.ROCK;
    }

    @Override
    public boolean beats(@Nonnull Shape otherShape) {
        return otherShape.getType().equals(ShapeType.SCISSORS);
    }
}
