package stream.ditigalStrems;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyDigitalStream {
    public static void main(String[] args) {
        Integer sum1 = Stream.of(1, 2, 3, 4)
                .reduce(0, Integer::sum);

        // это намного быстрее сработает
        int sum = IntStream.of(1, 2, 3, 4)
                .sum();

        // также мы можем превратить обычный Stream в IntStream через .mapToInt()
    }
}
