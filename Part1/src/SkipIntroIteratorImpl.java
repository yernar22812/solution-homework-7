public class SkipIntroIteratorImpl implements SkipIntroIterator {
    private final EpisodeIterator baseIterator;
    private final int skipSeconds;

    public SkipIntroIteratorImpl(EpisodeIterator baseIterator, int skipSeconds) {
        this.baseIterator = baseIterator;
        this.skipSeconds = skipSeconds;
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    @Override
    public EpisodeView next() {
        Episode episode = baseIterator.next();
        return new EpisodeView(episode, skipSeconds);
    }
}

