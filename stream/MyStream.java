package stream;

import func_interfaces.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MyStream {
    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static void main(String[] args) {
        /**
         * Intermediate operations
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
         * Terminal Operations
         * */
        menu.stream().forEach(System.out::println); // пробегаем по каждому элементу
        long count =
                menu.stream().count(); // считаем количество элементов
        boolean allBeef =
                menu.stream().allMatch(d -> d.name().equals("beef")); // проверяем все ли названия блюд соответствуют "beef"
        boolean anyBeef =
                menu.stream().anyMatch(d -> d.name().equals("beef")); // проверяем хоть одно название соответствует "beef"
        boolean beef =
                menu.stream().noneMatch(d -> d.name().equals("beef")); // проверяем, что ни одно название не соответствует "beef"
        Optional<Dish> first =
                menu.stream().findFirst(); // берем первый элемент
        Optional<Dish> any =
                menu.stream().findAny(); // берем любой элемент

        // reduce - уменьшать, сводить
        // В аргументах указываем: начальное значение и лямбду, которая группирует текущий и следующий элемент. */
        Integer sum = menu.stream()
                .map(Dish::calories)
                .reduce(0, (a, b) -> a + b); // найдем сумму каллаорий всей блюд

        // вариант reduce без начального значения возвращает Optional
        Optional<Integer> reduce = menu.stream()
                .map(Dish::calories)
                .reduce(Integer::sum); // здесь я использовал еще вместо обычной лямбды ссылку на суммирование
    }
}
