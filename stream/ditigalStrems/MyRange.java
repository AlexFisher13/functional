package stream.ditigalStrems;

import java.util.stream.IntStream;

public class MyRange {
    public static void main(String[] args) {
        /** Сгенерируем список четных чисел от 0 до 30 */
        /** range() - не включая последнее число */
        /** rangeClosed() - включая последнее число */

        IntStream.rangeClosed(1, 30)
                .filter(i -> i % 2 == 0)
                .forEach(i -> System.out.print(i + " "));

    }
}
