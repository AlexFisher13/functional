package stream.methods;

import func_interfaces.model.Dish;

import java.util.Arrays;

public class MyReduce {
    public static void main(String[] args) {

        /** Нужно просуммировать все элементы коллекции */
        Integer reduce = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(reduce);

        /** Посчитать общую каллорийность всего меню */
        Integer totalCcal = Dish.menu.stream()
                .map(Dish::calories)
                .reduce(0, (a, b) -> a + b);

        System.out.println(totalCcal);
    }
}
