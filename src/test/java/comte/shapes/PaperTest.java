package comte.shapes;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class PaperTest implements WithAssertions {

    @Test
    public void doesNotBeatScissors() throws Exception {
        Paper paper = new Paper();
        assertThat(paper.beats(new Scissors())).isFalse();
    }

    @Test
    public void doesNotBeatPaper() throws Exception {
        Paper paper = new Paper();
        assertThat(paper.beats(new Paper())).isFalse();
    }

    @Test
    public void beatsRock() throws Exception {
        Paper paper = new Paper();
        assertThat(paper.beats(new Rock())).isTrue();
    }

}