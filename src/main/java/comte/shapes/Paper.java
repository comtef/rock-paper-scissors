package comte.shapes;

import javax.annotation.Nonnull;

/**
 * Paper shape implementation
 */
class Paper implements Shape {

    @Override
    public ShapeType getType() {
        return ShapeType.PAPER;
    }


    @Override
    public boolean beats(@Nonnull Shape otherShape) {
        return otherShape.getType().equals(ShapeType.ROCK);
    }
}
