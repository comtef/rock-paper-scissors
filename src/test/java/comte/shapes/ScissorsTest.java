package comte.shapes;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class ScissorsTest implements WithAssertions {

    @Test
    public void doesNotBeatScissors() throws Exception {
        Scissors scissors = new Scissors();
        assertThat(scissors.beats(new Scissors())).isFalse();
    }

    @Test
    public void beatsPaper() throws Exception {
        Scissors scissors = new Scissors();
        assertThat(scissors.beats(new Paper())).isTrue();
    }

    @Test
    public void doesNotBeatRock() throws Exception {
        Scissors scissors = new Scissors();
        assertThat(scissors.beats(new Rock())).isFalse();
    }

}