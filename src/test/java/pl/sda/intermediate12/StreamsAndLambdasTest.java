package pl.sda.intermediate12;

import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsAndLambdasTest {
    private List<PersonForTest> people = Arrays.asList(
            new PersonForTest(1, "Anna", "Nowak", 33),
            new PersonForTest(2, "Beata", "Kowalska", 22),
            new PersonForTest(3, "Marek", "Nowak", 25),
            new PersonForTest(4, "Adam", "Twardowski", 33),
            new PersonForTest(5, "Monika", "Kos", 25),
            new PersonForTest(6, "Adam", "Rudy", 45),
            new PersonForTest(7, "Marek", "Rudy", 15)
    );

    private List<String> animals = Arrays.asList("cat", "dog", "  ", "mouse", null, "rat ", "pig", "rabbit", "  hamster", "parrot"); // Tradycyjna pętla

    @Test
    public void lambdas1() {
        int counter = 0;
        Comparator<String> comparator = (v, b) -> String.valueOf(b.charAt(1)).compareTo(String.valueOf(v.charAt(1)));
        List<String> list = animals.stream().filter(a -> a != null && !a.trim().isEmpty()).map(a -> a.trim()).sorted(comparator).collect(Collectors.toList());
        for (String animal : list) {
            counter++;
            if (animal == null || animal.trim().isEmpty()) {
                continue;
            }
            System.out.print(animal.trim() + (counter < list.size() ? ", " : "."));
        }
        System.out.println();
        System.out.println(animals.stream()
                .filter(animal -> animal != null && !animal.trim().isEmpty())
                .map(animal -> animal.trim())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(", ", "", ".")));

        System.out.println(people.stream()
                .filter(p -> p.getName().startsWith("A"))
                .map(e -> e.getName().toUpperCase())
                .distinct()
                .sorted((a, b) -> String.valueOf(a.charAt(a.length() - 1)).compareTo(String.valueOf(b.charAt(b.length() - 1))))
                .collect(Collectors.joining(", ")));

        Function<PersonForTest, String> personToFullNameFunction = a -> a.getName() + " " + a.getSurname();
        Comparator<PersonForTest> personForTestComparator = (l, m) -> l.getAge().compareTo(m.getAge());
        System.out.println(people.stream()
                .sorted(personForTestComparator)
                .map(personToFullNameFunction)
                .collect(Collectors.joining(", ")));

        Function<PersonForTest, Integer> function = e -> e.getAge();

        Map<Integer, List<PersonForTest>> collect1 = people.stream()
                .collect(Collectors.groupingBy(function));
        System.out.println(collect1);

        Map<Integer, PersonForTest> collect = people.stream()
                .collect(Collectors.toMap(e -> e.getIndex(), v -> v));

        Function<PersonForTest, String> getAgeWithEM = a -> a.getAge() + "!";
        Function<PersonForTest, String> anonymous = new Function<PersonForTest, String>() {
            @Override
            public String apply(PersonForTest personForTest) {
                return personForTest.getAge() + "!";
            }
        };


    }

    @Getter
    @ToString
    private class PersonForTest {
        private final Integer index;
        private final String name;
        private final String surname;
        private final Integer age;

        public PersonForTest(Integer index, String name, String surname, Integer age) {
            this.index = index;
            this.name = name;
            this.surname = surname;
            this.age = age;
        }
    }

    @FunctionalInterface
    public interface SuperChecker<T> {
        boolean check(T number);

    }

    private SuperChecker<Integer> checkIfOddLambda = a -> a % 2 != 0;
    private SuperChecker<Integer> checkIfOddAnonymous = new SuperChecker<Integer>() {
        @Override
        public boolean check(Integer number) {
            return number % 2 != 0;
        }
    };

    private SuperChecker<Integer> checkIfNumberIsLessThan5 = a -> a < 5;
    private SuperChecker<Integer> checkIfNumberIsLessThan5Annonymous = new SuperChecker<Integer>() {
        @Override
        public boolean check(Integer number) {
            return number < 5;
        }
    };

    @Test
    public void checker() {
        System.out.println(checkIfOddLambda.check(1));
        System.out.println(checkIfOddAnonymous.check(4));
        System.out.println(checkIfNumberIsLessThan5.check(7));
        System.out.println(checkIfNumberIsLessThan5Annonymous.check(4));

    }

    @FunctionalInterface
    public interface MathOperation {
        Integer calculate(Integer firstInt, Integer secondInt);
    }

    private MathOperation adder = (a, b) -> a + b;
    private MathOperation subtracker = (a, b) -> a - b;
    private MathOperation multiplier = (a, b) -> a * b;
    @Test
    public void mathOperationChecker(){
        System.out.println(adder.calculate(3, 8));
        System.out.println(subtracker.calculate(3, 8));
        System.out.println(multiplier.calculate(3, 8));
    }
}
