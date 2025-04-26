import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    public static void runBenchmark() {
        System.out.println("\nBenchmarking with 10,000 episodes...");

        Season bigSeason = new Season();
        for (int i = 0; i < 10000; i++) {
            bigSeason.addEpisode(new Episode("Episode " + i, 1500));
        }

        measureIterator("Normal Iterator", bigSeason.createSeasonIterator());
        measureIterator("Reverse Iterator", bigSeason.createReverseSeasonIterator());
        measureIterator("Shuffle Iterator", bigSeason.createShuffleSeasonIterator());
    }

    private static void measureIterator(String name, EpisodeIterator iterator) {
        long start = System.nanoTime();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        long end = System.nanoTime();
        System.out.printf("%-20s: %,d episodes in %.2f ms%n", name, count, (end - start) / 1_000_000.0);
    }
}
