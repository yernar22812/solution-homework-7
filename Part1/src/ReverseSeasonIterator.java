import java.util.List;

public class ReverseSeasonIterator implements EpisodeIterator {
    private final List<Episode> episodes;
    private int position;

    public ReverseSeasonIterator(Season season) {
        this.episodes = season.getEpisodes();
        this.position = episodes.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public Episode next() {
        return episodes.get(position--);
    }
}