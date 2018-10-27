package pl.sda.intermediate12;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
         List<String> list = animals.stream().filter(a -> a != null&&!a.trim().isEmpty()).map(a -> a.trim()).sorted(comparator).collect(Collectors.toList());
        for (String animal : list) {
            counter++;
            if (animal == null || animal.trim().isEmpty()) {
                continue;
            }
            System.out.print(animal.trim()+(counter<list.size()?", ":"."));
        }
        System.out.println();
        System.out.println(animals.stream()
                .filter(animal -> animal != null && !animal.trim().isEmpty())
                .map(animal -> animal.trim())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(", ","",".")));
    }

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
}
