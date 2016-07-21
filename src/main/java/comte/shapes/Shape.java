package comte.shapes;

import javax.annotation.Nonnull;

/**
 * Shape chosen by players during game turns
 */
public interface Shape {

    /**
     * Concrete type of the shade
     *
     * @return the {@link ShapeType}
     */
    ShapeType getType();

    /**
     * Indicate if this shape beats another shape
     *
     * @param otherShape other shape to fight against
     * @return true if the shape is beaten, false otherwise (loss, tie)
     */
    boolean beats(@Nonnull Shape otherShape);

}
