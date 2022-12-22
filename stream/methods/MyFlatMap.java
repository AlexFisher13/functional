package stream.methods;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MyFlatMap {
    public static void main(String[] args) {
        /** Получить из этого ["Hello," "World"] это ["H," "e," "l," "o," "W," "r," "d"] */
        List<String> helloWorld = Arrays.asList("Hello", "World");

        helloWorld.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        // в таком случае мы получим список из двух стринговых массива (String[], String[])

        helloWorld.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream) // преобразует массив в поток
                .distinct()
                .collect(toList());
        // но так тоже не получится, так как у нас будет список потоков List<Stream<String>>

        helloWorld.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // преобразует массив в поток
                .distinct()
                .collect(toList());
        // а вот теперь все сработает, так как flatMap сольет воедино 2 потока данных данных Stream<String>


        /** По двум заданным спискам чисел вернуть все их попарные сочетания.
         Например, при получении списков [1, 2, 3] и [3, 4] нужно вернуть [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
         Для простоты можно представить каждую пару в виде массива из двух элементов. */
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> ints = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .toList();


        /** Как бы вы обобщили предыдущий пример так, чтобы возвращались только те пары, сумма которых делится на 3? */
        List<int[]> ints2 = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> i + j % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .toList();

    }
}
