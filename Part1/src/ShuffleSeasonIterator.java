import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffledEpisodes;
    private int position = 0;

    public ShuffleSeasonIterator(Season season) {
        this.shuffledEpisodes = new ArrayList<>(season.getEpisodes());
        Collections.shuffle(shuffledEpisodes, new Random(42)); // фиксированное зерно для повторяемости
    }

    @Override
    public boolean hasNext() {
        return position < shuffledEpisodes.size();
    }

    @Override
    public Episode next() {
        return shuffledEpisodes.get(position++);
    }
}
