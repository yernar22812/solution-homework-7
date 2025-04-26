import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Season season1 = new Season();
        season1.addEpisode(new Episode("S1E1", 1500));
        season1.addEpisode(new Episode("S1E2", 1400));

        Season season2 = new Season();
        season2.addEpisode(new Episode("S2E1", 1600));
        season2.addEpisode(new Episode("S2E2", 1550));

        Series series = new Series();
        series.addSeason(season1);
        series.addSeason(season2);

        System.out.println("Normal order:");
        EpisodeIterator normalIterator = season1.createSeasonIterator();
        while (normalIterator.hasNext()) {
            System.out.println(normalIterator.next().getTitle());
        }

        System.out.println("\nReverse order:");
        EpisodeIterator reverseIterator = season1.createReverseSeasonIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next().getTitle());
        }

        System.out.println("\nShuffle order:");
        EpisodeIterator shuffleIterator = season1.createShuffleSeasonIterator();
        while (shuffleIterator.hasNext()) {
            System.out.println(shuffleIterator.next().getTitle());
        }

        System.out.println("\nBinge watch (all seasons):");
        EpisodeIterator bingeIterator = series.createBingeIterator();
        while (bingeIterator.hasNext()) {
            System.out.println(bingeIterator.next().getTitle());
        }

        System.out.println("\nSkip Intro (start from 60s):");
        SkipIntroIterator skipIntroIterator = new SkipIntroIteratorImpl(season1.createSeasonIterator(), 60);
        while (skipIntroIterator.hasNext()) {
            EpisodeView view = skipIntroIterator.next();
            view.play();
        }
        System.out.println("\nWatch only unseen episodes:");
        Set<String> watched = new HashSet<>();
        watched.add("S1E1");
        EpisodeIterator watchHistoryIterator = new WatchHistoryIterator(season1.createSeasonIterator(), watched);
        while (watchHistoryIterator.hasNext()) {
            System.out.println(watchHistoryIterator.next().getTitle());
        }

        Benchmark.runBenchmark(); // Stretch goal: time test
    }
}
