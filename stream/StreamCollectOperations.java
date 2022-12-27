package stream;

import func_interfaces.model.Dish;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class StreamCollectOperations {
    /**
     *
     * Collect - "собирать"
     * Collector<T, A, R> - интерфейс для создания своих стратегий сборки
     * Collectors - класс с готовыми стратегиями сборки (toList, joining итд), реализующий Collector<T, A, R> в своих static методах
     *    - toList
     *    - joining
     *    - counting
     *    - maxBy, minBy
     *    - summingInt, summingLong, summingDouble
     *    - averagingInt, averagingLong, averagingDouble
     *    - summarizingInt, summarizingLong, summarizingDouble
     *    - reducing, filtering, summing, mapping
     *    - groupingBy
     *    - partitioningBy
     *    - countingAndThen
     * */

    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {

        // toList, toSet, toMap - самый популярный коллектор
        List<Dish> toList = menu.stream().collect(toList());

        // toCollection, toUnmodifibleList - частные случаи коллекций
        List<Dish> result = menu.stream()
                .collect(toCollection(LinkedList::new));

        // joining - соединяет все элементы в строку, разделив сепараторм при необходимости
        String joining = menu.stream().map(Dish::name)
                .collect(joining(", "));

        // counting - делает тоже самое, что и Stream.count()
        Long counting = menu.stream().collect(counting());

        // maxBy, minBy делают то же самое, что и Stream.reduce(Integer::max)
        Optional<Dish> maxBy = menu.stream().collect(maxBy(comparingInt(Dish::calories)));

        /* Следующие методы, делают то же самое что и соответствующие методы числовых стримов IntStream, итд
              - summingInt, summingLong, summingDouble
              - averagingInt, averagingLong, averagingDouble
              - summarizingInt, summarizingLong, summarizingDouble */
        IntSummaryStatistics statistics = menu.stream().map(Dish::calories)
                .collect(summarizingInt(Integer::intValue));

        // reducing, filtering, summing, mapping - делают аналогично одноименным методам Stream
        Integer sum = menu.stream().map(Dish::calories)
                .collect(reducing(0, Integer::sum));

        // groupBy создает группы, которые представляют собой бакеты мапы, и суть группировки отражается в ключе
        Map<Dish.Type, List<Dish>> dishGroupingByType =
                menu.stream().collect(groupingBy(Dish::type)); // группируем блюда по типу
        dishGroupingByType.forEach((k, v) -> System.out.println(k + ": " + v));

        // partitioningBy это группировка на 2 части (true/false)
        Map<Boolean, List<Dish>> collect =
                menu.stream().collect(partitioningBy(Dish::vegetarian)); // разделяем блюда на вегетарианские и обычные

        // countingAndThen - создавать цепочку операций collect, а также позволяет безопасно извлечь значение из Optional
        menu.stream()
                .collect(groupingBy(Dish::type, collectingAndThen( // группируем блюда по типу,
                        maxBy(comparingInt(Dish::calories)), Optional::get))) // и в каждой группе находим самое каллорийное
                .forEach((k, v) -> System.out.println(k + " - " + v));



        /**
         *
         * Collector <T, A, R> - создаем свою стратегию сборки
         * collect(supplier(поставщик),
         *         accumulator(накопитель)
         *         combiner(объединитель));
         */
        ArrayList<Dish> dishes = menu.stream()
                .collect(ArrayList::new, // метод Supplier<A> supplier() - сначала создаем контейнер для хранения
                        List::add,      // метод BiConsumer<A, T> accumulator() - указываем как и какие элементы добавлять
                        List::addAll); // метод BinaryOperator<A> combiner() - указываем как эти элементы объединить

    }
}
