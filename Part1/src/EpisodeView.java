public class EpisodeView {
    private final Episode episode;
    private final int startSecond;

    public EpisodeView(Episode episode, int startSecond) {
        this.episode = episode;
        this.startSecond = startSecond;
    }

    public void play() {
        System.out.println("Playing " + episode.getTitle() + " from " + startSecond + " second.");
    }
}