import java.util.Set;

public class WatchHistoryIterator implements EpisodeIterator {
    private final EpisodeIterator baseIterator;
    private final Set<String> watchedTitles; // запоминаем по названию эпизода

    public WatchHistoryIterator(EpisodeIterator baseIterator, Set<String> watchedTitles) {
        this.baseIterator = baseIterator;
        this.watchedTitles = watchedTitles;
    }

    @Override
    public boolean hasNext() {
        while (baseIterator.hasNext()) {
            Episode next = baseIterator.next();
            if (!watchedTitles.contains(next.getTitle())) {
                bufferedEpisode = next;
                hasBuffered = true;
                return true;
            }
        }
        return false;
    }

    private Episode bufferedEpisode;
    private boolean hasBuffered = false;

    @Override
    public Episode next() {
        if (hasBuffered) {
            hasBuffered = false;
            return bufferedEpisode;
        }
        return baseIterator.next();
    }
}