package stream.methods;

import java.util.Arrays;
import java.util.List;

public class MyMap {
    public static void main(String[] args) {
        /** По заданному списку чисел вернуть список их квадратов.
         Например, при списке [1, 2, 3, 4, 5] необходимо вернуть [1, 4, 9, 16, 25].*/

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> list = Arrays.stream(arr)
                .map(i -> i * i)
                .toList();
    }
}
