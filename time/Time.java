package time;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Time {
    /* В Java 1.0 появился класс Date (миллисекунды от 1900), который уже в Java 1.1 пометили deprecated

    * В Java 1.1 добавили класс Calendar, который только решал проблему отсчета времени с 1900,
    * но имел ряд конструктивных проблем (например месяцы начинались с 0), которые приводили к ошибкам

    * В Java 8 добавили пакет java.time включает множество новых классов:
    *  LocalDate, LocalTime, LocalDateTime, Instant, Duration и Period, ZoneOffset, ChronoUnit */

    public static void main(String[] args) {
        /**
         * Время и дата БЕЗ часового пояса: LocalDate, LocalTime, LocalDateTime
         * */
        LocalDate.now(); // дата сейчас
        LocalDate date = LocalDate.of(2022, 12, 28);
        LocalDate parseDate = LocalDate.parse("2017-12-28");
        LocalTime parseTime = LocalTime.parse("12:24:15");

        Month month = date.getMonth(); // DECEMBER
        DayOfWeek dayOfWeek = date.getDayOfWeek();// WEDNESDAY
        int year = date.getYear(); // 2022
        int dayOfYear = date.getDayOfYear(); // 362
        int dayOfMonth = date.getDayOfMonth(); // 28
        boolean leapYear = date.isLeapYear(); // високосный? - false
        int lengthOfMonth = date.lengthOfMonth(); // 31

        LocalTime time = LocalTime.of(12, 24, 15); // 12:24:15
        int minute = time.getMinute();// 24

        /**
         *  Операции над датами LocalDate, LocalTime, LocalDateTime
         * */
        date.plusWeeks(2);
        date.minusYears(1);
        date.minus(2, ChronoUnit.CENTURIES);


        /**
         * Instant - современный аналог Date, который хранит время в милисекундах от 1970
         * */
        Instant now = Instant.now();

        /**
         * Определяем разницу во времени
         * Duration - класс для определения разницы для ВРЕМЕНИ в секундах
         * Period - класс для определения разницы для ДАТ в днях
         * */

        Duration between =
                Duration.between(LocalTime.of(22, 12, 2), LocalTime.of(22, 12, 6));

        Period period =
                Period.between(LocalDate.of(2022, 12, 2), LocalDate.of(2022, 12, 22));

        /**
         *
         * ZoneId - Часовой пояс.
         * Путем сочетания объекта ZoneId с LocalDate, LocalDateTime или Instant можно преобразовать его
         * в объект ZonedDateTime, соответствующий моменту времени относительно выбранного часового пояса.
         *
         * */

        ZoneId romeZone = ZoneId.of("Europe/Rome");

        // Преобразуем LocalDateTime в Instant относительно часовой зоны по Гринвичу
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        Instant instantFromDateTime = dateTime.toInstant(ZoneOffset.UTC); // Часовая зона
    }
}
