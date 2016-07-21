package comte.shapes;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class RockTest implements WithAssertions {

    @Test
    public void beatsScissors() throws Exception {
        Rock rock = new Rock();
        assertThat(rock.beats(new Scissors())).isTrue();
    }

    @Test
    public void doesNotBeatPaper() throws Exception {
        Rock rock = new Rock();
        assertThat(rock.beats(new Paper())).isFalse();
    }

    @Test
    public void doesNotBeatRock() throws Exception {
        Rock rock = new Rock();
        assertThat(rock.beats(new Rock())).isFalse();
    }
}