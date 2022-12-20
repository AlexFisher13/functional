package func_interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/* Условие */
public class MyPredicate {

    // Мы принимаем в аргументах и ПОВЕДЕНИЕ и аругмент
    public static boolean check(Predicate<Integer> predicate, Integer param) {
        return predicate.test(param);
    }

    public static void main(String[] args) {
        int param = 100;
        // И теперь передавая разное ПОВЕДЕНИЕ мы ПЕРЕИСПОЛЬЗУЕМ один и тот же метод
        System.out.println(check(a -> a > 5, param));
        System.out.println(check(a -> a == 0, param));
        System.out.println(check(a -> a % 2 != 0, param));
    }

    // Далее рассмотрим цепочки предикатов, который можно делать благодаря методов по умолчанию
    record Cat(String name, int age, String color){}

    Predicate<Cat> predicate = cat -> cat.color.equals("black");
    Predicate<Cat> predicate2 = cat -> cat.color.equals("red");
    Predicate<Cat> predicate3 = cat -> cat.age > 5;

    public List<Cat> triplePredicate(List<Cat> cats) {
        return cats.stream()
                .filter(predicate
                        .or(predicate2)
                        .and(predicate3)
                        .negate())
                .collect(toList());
    }

    // Редкий вариант со списком предикатов
    public List<Cat> listOfPredicates(List<Cat> cats) {
        List<Predicate<Cat>> allPredicates = Arrays.asList(predicate, predicate2, predicate3);
        return cats.stream()
                .filter(allPredicates.stream().reduce(x -> true, Predicate::and))
                .collect(toList());
    }


}