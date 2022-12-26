package stream;

import func_interfaces.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

        /* Intermediate operations */
        long count = menu.stream()
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
    }
}
