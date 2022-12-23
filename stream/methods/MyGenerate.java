package stream.methods;

import java.util.stream.Stream;

public class MyGenerate {
    public static void main(String[] args) {
        /** Generate отличается от iterate тем, что испоользует в аргументе Supplier (Поставщик) */

        /** Сгенерировать 5 рандомных чисел */
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
