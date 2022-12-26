package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class MyCollector {
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(250d, Currency.EUR),
            new Transaction(250d, Currency.EUR),
            new Transaction(250d, Currency.USD),
            new Transaction(250d, Currency.USD),
            new Transaction(250d, Currency.RUB),
            new Transaction(250d, Currency.RUB),
            new Transaction(250d, Currency.RUB));


    public static void main(String[] args) {
        /** Сгруппировать транзакции по валютам */
        Map<Currency, List<Transaction>> transactionsByCurrencies =
                transactions.stream().collect(groupingBy(Transaction::getCurrency));
        transactionsByCurrencies.forEach((k, v) -> System.out.println(k + " - " + v));
    }

    private enum Currency {
        USD, EUR, RUB
    }

    private static class Transaction {
        private Double sum;
        private Currency currency;

        public Transaction(Double sum, Currency currency) {
            this.sum = sum;
            this.currency = currency;
        }

        public Currency getCurrency() {
            return currency;
        }

        @Override
        public String toString() {
            return "{sum=" + sum + ", currency=" + currency + '}';
        }
    }
}
