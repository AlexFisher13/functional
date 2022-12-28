package optional;

import optional.model.*;

import java.util.Optional;

public class OptionalOperations {
    public static void main(String[] args) {

        /**
         * Самый безопасный способ работы с Optional это
         * создание через Optional.ofNullable()
         * получение значений через map(function).orElse() или когда нужно flatMap
         * если нам ненужно ничего возвращать, то ifPresent(consumer) или ifPresentOrElse()
         * и filter(predicate) для создания условий
         * */


        Person person = new Person();

        Optional.empty();
        Optional.of(person); // бросает NPE, если person == null
        Optional.ofNullable(person); // NPE не бросает

        Optional.ofNullable(person).get(); // НЕ безопасное получение значения, бросает NoSuchElementExc при null

        /**
         * Безопасное извлечение значений
         */
        // map, как и в Stream принимает функцию, которая преобразует элемент в случае если он не null,
        // если всё таки null, то метод map ничего не сделает
        String personsCarName = Optional.ofNullable(person) // если person == null, то результат "нет машины"
                .map(Person::getCar)  // если car == null, то результат "нет машины"
                .map(Car::getName)   // ecли name == null, то результат "нет машины"
                .orElse("нет машины");
        // то есть на каком бы этапе у нас не возник null, мы не получим NPE, а получим результат orElse. Это супербезопасно!

        /**
         * Извлечение из вложенных опшиналов
         */
        // flatMap, как и в стримах нужен для того чтобы двумерное сделать плоским, а именно Optional<Optional<T>>
        // Создадим модели у которых поля будут Optional. А именно класс Byhta с Optional<Boat> и Boat c Optional<Insurance>
        Byhta byhta = null;
        String insuranceName = Optional.ofNullable(byhta) // тут мы получаем Optional<Buhta>
                .flatMap(Byhta::getBoat) // при получении лодки у нас получается Optional<Optional<Boat>>, но flatMap делает из него одномерный Optional<Boat>
                .flatMap(Boat::getInsurance) // аналогично тут Optional<Optional<Insurance>>, и опять мы делаем Optional одномерным
                .map(Insurance::getName) // name имеет тип String, поэтому просто его получаем через map
                .orElse("Unknown");
    }

    /**
     * Условия в опшиналах - filer
     * filter в опшиналах может помочь проверить объект на соответствие какому-то предикату,
     * и тольео в случает соотвествия пойти дальше
     */
    private static void myFilter() {
        Insurance insurance = null;
        Optional.ofNullable(insurance)
                .filter(i -> i.getName().equals("Alfa")) // если имя страховки соотвествует условию, только тогда мы пойдем дальше
                .ifPresent(System.out::println); // в противном случе мы просто ничего не выполним. Ошибок не будет
    }

    /**
     * Задание: Выводить имя страховой если клиент старше 18 лет
     */
    public static void getInsuranceNameIfPersonAdult(Person person) {
        Optional.ofNullable(person)
                .filter(p -> p.getAge() > 18) // только если фильтр выполнится, то идем дальше
                .map(Person::getCar) // берем машину если есть
                .map(Car::getInsurance) // берем страховку если есть
                .ifPresent(System.out::println); // выводим имя страховки если есть
    }

    /**
     * Задание: Написать метод, который подбирает страховку, по двум переданным параметрам Person и Car.
     * Метод должен вернуть объект Optional<Insurance>, который будет пуст, если пусто хотя бы одно из переданных значений.
     */
    public Optional<Insurance> findInsurance(Optional<Person> person, Optional<Car> car) {
        return person
                .flatMap(p -> car.map(c -> findCheepestInsurance(p, c)));
    }

    private Insurance findCheepestInsurance(Person p, Car c) {
        // some logic
        return null;
    }
}
