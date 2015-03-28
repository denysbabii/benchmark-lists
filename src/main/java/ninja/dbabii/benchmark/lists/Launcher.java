package ninja.dbabii.benchmark.lists;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Launcher {

    public static void main(String... args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkLists.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .threads(2)
                .build();

        new Runner(opt).run();
    }

}
