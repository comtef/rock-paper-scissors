package comte.shapes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Utility holder for all shapes
 */
public class Shapes {

    /**
     * All shapes implemented in the game
     */
    public static final List<Shape> ALL_SHAPES = Arrays.asList(
            new Paper(),
            new Rock(),
            new Scissors()
    );

    /**
     * Get a random shape from {@link Shapes#ALL_SHAPES}
     *
     * @return A random shape
     */
    public static Shape getRandomShape() {
        return ALL_SHAPES.get(new Random().nextInt(ALL_SHAPES.size()));
    }
}
