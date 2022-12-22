package stream.tasks;

import stream.tasks.model.Trader;
import stream.tasks.model.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static Trader raoul = new Trader("Raoul", "Cambridge");
    public static Trader mario = new Trader("Mario","Milan");
    public static Trader alan = new Trader("Alan","Cambridge");
    public static Trader brian = new Trader("Brian","Cambridge");

    public static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950));

    public static void main(String[] args) {
        /** 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).*/
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        /** 2. Вывести список неповторяющихся городов, в которых работают трейдеры. */
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        /** 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам. */
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        /** 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке. */
        String names = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted(String::compareTo)
                .collect(Collectors.joining(", "));
        System.out.println(names);

        /** 5. Выяснить, существует ли хоть один трейдер из Милана. */
        boolean traderFromMilan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println("Есть ли трейдер в милане? " + traderFromMilan);

        /** 6. Вывести суммы всех транзакций трейдеров из Кембриджа. */
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        /** 7. Какова максимальная сумма среди всех транзакций? */
        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(max);

        /** 8. Найти транзакцию с минимальной суммой. */
        Optional<Transaction> min = transactions.stream()
                .reduce((a, b) -> a.getValue() > b.getValue() ? a : b);

        System.out.println(min.get());
    }
}
