import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Season implements Iterable<Episode> {
    private final List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public EpisodeIterator createSeasonIterator() {
        return new SeasonIterator(this);
    }

    public EpisodeIterator createReverseSeasonIterator() {
        return new ReverseSeasonIterator(this);
    }

    public EpisodeIterator createShuffleSeasonIterator() {
        return new ShuffleSeasonIterator(this);
    }

    @Override
    public Iterator<Episode> iterator() {
        return episodes.iterator(); // для foreach
    }
}
