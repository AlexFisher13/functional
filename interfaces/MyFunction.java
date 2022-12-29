package interfaces;

import interfaces.model.Cat;

import java.util.function.Function;

/* Функция */
public class MyFunction {

    // Мы принимаем в аргументах и ПОВЕДЕНИЕ и аругмент
    public static String func(Function<Cat, String> function, Cat cat) {
        return function.apply(cat);
    }

    // Примеры передачи разного поведения
    public static void main(String[] args) {
        Cat cat = new Cat("black", 3);

        Function<Cat, String> getColors = Cat::color;
        Function<Cat, String> getAges = c -> String.valueOf(c.age());

        System.out.println(func(getColors, cat));
        System.out.println(func(getAges, cat));

        Function<Integer, Integer> f1 = x -> x + 5;
        Function<Integer, Integer> f2 = x -> x * 2;

        // Дефолтные методы Functional
        Function<Integer, Integer> f3 = f1.andThen(f2);
        Function<Integer, Integer> f4 = f1.compose(f3);
    }


}
