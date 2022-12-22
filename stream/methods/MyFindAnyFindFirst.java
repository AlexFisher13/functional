package stream.methods;

import func_interfaces.model.Dish;

import java.util.Optional;

public class MyFindAnyFindFirst {
    public static void main(String[] args) {
        /** Найти хоть одно вегетарианское блюдо
         * результат Optional, потому что результата может и не быть*/
        Optional<Dish> any = Dish.menu.stream()
                .filter(Dish::vegetarian)
                .findAny();

        /** Найти первое вегетарианское блюдо
         * результат также Optional*/
        Optional<Dish> first = Dish.menu.stream()
                .filter(Dish::vegetarian)
                .findFirst();
    }
}
