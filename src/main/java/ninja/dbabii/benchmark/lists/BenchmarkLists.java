package ninja.dbabii.benchmark.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 * More details on http://openjdk.java.net/projects/code-tools/jmh/
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkLists {

//    private static final int INIT_SIZE = 1024;
//    private final Integer[] ints = new Integer[INIT_SIZE];
//    private final List<Integer> lList = new LinkedList<>();
//    private final List<Integer> aList = new ArrayList<>();
//    private final Random random = new Random();
    int INIT_SIZE = 1024;
    Integer[] ints = new Integer[INIT_SIZE];
    List<Integer> lList = new LinkedList<>();
    List<Integer> aList = new ArrayList<>();
    Random random = new Random();

    @Setup
    public void initInts() {
        for (int i = 0; i < INIT_SIZE; i++) {
            ints[i] = random.nextInt();
        }
    }

    @Setup
    public void initLists() {
        for (int i = 0; i < INIT_SIZE; i++) {
            int add = random.nextInt();
            aList.add(add);
            lList.add(add);
        }
    }

    @Benchmark
    public List<Integer> addAllLinkedList() {
        List<Integer> ret = new LinkedList<>();
        ret.addAll(Arrays.asList(ints));
        return ret;
    }

    @Benchmark
    public List<Integer> addAllArrayList() {
        List<Integer> ret = new ArrayList<>();
        ret.addAll(Arrays.asList(ints));
        return ret;
    }

    @Benchmark
    public List<Integer> addAllArrayListWithCapacity() {
        List<Integer> ret = new ArrayList<>(ints.length);
        ret.addAll(Arrays.asList(ints));
        return ret;
    }

    @Benchmark
    public List<Integer> addFirstLinkedList() {
        List<Integer> ret = new LinkedList<>();
        for (Integer i : ints) {
            ret.add(0, i);
        }
        return ret;
    }

    @Benchmark
    public List<Integer> addFirstArrayList() {
        List<Integer> ret = new ArrayList<>();
        for (Integer i : ints) {
            ret.add(0, i);
        }
        return ret;
    }

    @Benchmark
    public List<Integer> addFirstArrayListWithCapacity() {
        List<Integer> ret = new ArrayList<>(ints.length);
        for (Integer i : ints) {
            ret.add(0, i);
        }
        return ret;
    }

    @Benchmark
    public List<Integer> addToCenterLinkedList() {
        List<Integer> ret = new LinkedList<>();
        for (Integer i : ints) {
            ret.add(ret.size() / 2, i);
        }
        return ret;
    }

    @Benchmark
    public List<Integer> addToCenterArrayList() {
        List<Integer> ret = new ArrayList<>();
        for (Integer i : ints) {
            ret.add(ret.size() / 2, i);
        }
        return ret;
    }

    @Benchmark
    public List<Integer> addToCenterArrayListWithCapacity() {
        List<Integer> ret = new ArrayList<>(ints.length);
        for (Integer i : ints) {
            ret.add(ret.size() / 2, i);
        }
        return ret;
    }

    @Benchmark
    public long listLinkedListWithForEach() {
        long ret = 0;
        for (Integer i : lList) {
            ret += i;
        }
        return ret;
    }

    @Benchmark
    public long listArrayListWithForEach() {
        long ret = 0;
        for (Integer i : aList) {
            ret += i;
        }
        return ret;
    }

    @Benchmark
    public long listLinkedListWithOldFor() {
        long ret = 0;
        for (int i = 0, size = lList.size(); i < size; i++) {
            ret += lList.get(i);
        }
        return ret;
    }

    @Benchmark
    public long listArrayListWithOldFor() {
        long ret = 0;
        for (int i = 0, size = aList.size(); i < size; i++) {
            ret += aList.get(i);
        }
        return ret;
    }

    @Benchmark
    public List<Integer> removeOddLinkedList() {
        List<Integer> ret = new LinkedList<>();

        for (Iterator<Integer> it = lList.iterator(); it.hasNext();) {
            Integer next = it.next();
            if (next % 2 != 0) {
                ret.add(next);
                it.remove();
            }
        }

        return ret;
    }

    @Benchmark
    public List<Integer> removeOddArrayList() {
        List<Integer> ret = new ArrayList<>();

        for (Iterator<Integer> it = aList.iterator(); it.hasNext();) {
            Integer next = it.next();
            if (next % 2 != 0) {
                ret.add(next);
                it.remove();
            }
        }

        return ret;
    }
}
