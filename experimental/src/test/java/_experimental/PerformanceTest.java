package _experimental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class PerformanceTest {

    private static final int MAX = 100;
    private Map<String, List<Long>> average;

    @Test
    public void test() {
        average = new HashMap<>();
        for (int j = 0; j < 50000; j++) {
            testInternal();
        }
//        average.entrySet().stream()
//                // TODO reduce
//                .forEach(e -> System.out.println(
//                                String.format("%s -> %.10f",
//                                        e.getKey(),
//                                        e.getValue().stream().mapToLong(t -> t).average().getAsDouble()
//                                )
//                        )
//                );
        average.entrySet().stream()
                .map(e -> {
                    return String.format("%s -> %.10f",
                            e.getKey(),
                            e.getValue().stream().mapToLong(t -> t).average().getAsDouble()
                    );
                })
                .sorted()
                .forEach(System.out::println);
    }

    private void testInternal() {
        if (average == null) {
            throw new IllegalStateException("average must not be null");
        }
        final String key = String.format("%s(%s)", "key1", "key2");
        if (!average.containsKey(key)) {
            average.put(key, new ArrayList<>());
        }
        final long s = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            // TODO
        }
        final long e = System.currentTimeMillis();
        average.get(key).add((e - s));
    }
}
