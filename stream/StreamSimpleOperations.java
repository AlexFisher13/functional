package stream;

import interfaces.model.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSimpleOperations {
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

    public static void main(String[] args) throws IOException {
        /**
         *
         * Intermediate operations
         *
         */
        long dishCount = menu.stream()
                .filter(d -> d.calories() > 100) // отбираем блюда калорийностью больше 100
                .peek(System.out::println) // подглядываем в поток, и выводим в консоль элементы
                .sorted(Comparator.comparingInt(Dish::calories)) // сортируем по каллориям
                .skip(2) // пропускаем первые 2 элемента
                .limit(5) // отрезаем все элементы после пятого
                .map(Dish::name) // оставляем только имена блюд
                .takeWhile(n -> n.contains("c")) // отбираем элементы пока они содержат "c"
                .dropWhile(n -> n.contains("t")) // выкидываем элементы, которые содержат "t"
                .distinct() // оставляем только уникальные элементы
                .count();

        // flatMap. Перемножаем 2 списка
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(10, 20, 30, 40);
        list1.stream()
                .flatMap(i -> list2.stream()  // сливаем 2 потока в один
                        .map(j -> i * j))
                .forEach(i -> System.out.print(i + ", "));

        /**
         *
         * Terminal Operations
         *
         * */
        menu.stream().forEach(System.out::println); // пробегаем по каждому элементу
        long count =
                menu.stream().count(); // считаем количество элементов
        boolean allMatch =
                menu.stream().allMatch(d -> d.name().equals("beef")); // проверяем все ли названия блюд соответствуют "beef"
        boolean anyMatch =
                menu.stream().anyMatch(d -> d.name().equals("beef")); // проверяем хоть одно название соответствует "beef"
        boolean noneMatch =
                menu.stream().noneMatch(d -> d.name().equals("beef")); // проверяем, что ни одно название не соответствует "beef"
        Optional<Dish> findFirst =
                menu.stream().findFirst(); // берем первый элемент
        Optional<Dish> findAny =
                menu.stream().findAny(); // берем любой элемент

        // reduce - уменьшать, сводить
        // В аргументах указываем: начальное значение и лямбду, которая группирует текущий и следующий элемент. */
        Integer sum = menu.stream()
                .map(Dish::calories)
                .reduce(0, (a, b) -> a + b); // найдем сумму каллаорий всей блюд

        // вариант reduce без начального значения возвращает Optional
        Optional<Integer> max = menu.stream()
                .map(Dish::calories)
                .reduce(Integer::max); // здесь я использовал еще вместо обычной лямбды ссылку на поиск максимального


        /**
         *
         * Generate Operations
         *
         * */
        /* iterate - создает бесконечный поток.
        Принимает 2 параметра: начальное число и функцию создающее следующее число на основе предыдущего.
        Такой бесконечный поток нужно ограничивать, с помощью limit().*/
                Stream.iterate(100, n -> n + 3) // генерировать числа начиная со 100, прибавляя по 3
                .limit(100) // без лимита будет бесконечный поток
                .forEach(System.out::println);

        /* Сгенерировать 20 чисел Фибоначи (когда следующее число сумма двух предыдущих)!
         * Сложность в том, что iterate использует один элемент для генерации следующего, нужно для начала 2 числа.
         * Нам же нужно работать именно с парами чисел, для вычисления их сумм.
         * Поэтому, в качестве элемента мы будем использовать в качествое элемента ОДИН массив, из двух значений. */
        Stream.iterate(new int[]{0, 1},
                        t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        /* В Java 9 в iterate добавили поддержку предикатов,
        то есть можно генерить, пока не выполнится какое-то условие.
        (Начальное значение, условие, шаг) */
        Stream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);

        /* generate - создает бесконечный поток.
        Принимает параметр Supplier - поставщик.
        Например, Math::random. Нам будет бесконечно генерить рандомные числа.
        Также нужно ограничивать лимитом.
         */
        Stream.generate(Math::random) // Сгенерировать 5 рандомных чисел
                .limit(5)
                .forEach(System.out::println);


        /* range, rangeClosed - генерирует нам ограниченный поток чисел.
        Принимает на вход: начальное и конечное значение.
        Разница в том, что range не включает указанные конечный элемент, а rangeClosed включает.
        И это все работает только числовыми стримами (IntStream, DoubleStream, LongStream)*/
        IntStream.rangeClosed(1, 30) // Сгенерируем список четных чисел от 0 до 30
                .forEach(i -> System.out.print(i + " "));


        /**
         *
         * Числовые стримы IntStream, LongStream, DoubleStream
         * оптимизированы для работы именно со своими типами данных.
         *
         * */

        IntStream intStream = IntStream.of(1, 2, 3, 4);
        IntStream intStream2 = Stream.of(1, 2, 3).mapToInt(Integer::valueOf);

        intStream.sum(); // сумма
        intStream.max(); // макс/мин
        intStream.average(); // среднее
        intStream.summaryStatistics(); // статистика включает в себя сумму, макс, мин, средее
        intStream.boxed(); // трансформирует в обычный Stream



        /**
         *
         * Стрим из файла
         *
         * */
        Path path = Paths.get("/Users/fisher/IdeaProjects/functional/stream/nio/data.txt");

        long uniqueWords = Files.lines(path, Charset.defaultCharset()) // создаем стрим строк файла
                .flatMap(line -> Arrays.stream(line.split(" "))) // из каждой строки создаем стрим слов. И сливаем эти стримы в один
                .distinct() // находим уникальные слова
                .count();

        System.out.println(uniqueWords);
    }
}
