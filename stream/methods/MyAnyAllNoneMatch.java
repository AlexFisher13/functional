package stream.methods;

import func_interfaces.model.Dish;

public class MyAnyAllNoneMatch {
    public static void main(String[] args) {
        /** Есть ли среди блюд хоть одно вегетарианское */
        boolean anyVegan = Dish.menu.stream()
                .anyMatch(Dish::vegetarian);

        System.out.println(anyVegan);

        /** Во все ли блюдах больше 300 ккал */
        boolean more300ccal = Dish.menu.stream()
                .allMatch(d -> d.calories() > 300);

        System.out.println(more300ccal);

        /** Проверить нет ли блюд больше 5000 ккал */
        boolean no5000ccal = Dish.menu.stream()
                .noneMatch(d -> d.calories() > 5000);

        System.out.println(no5000ccal);
    }
}
