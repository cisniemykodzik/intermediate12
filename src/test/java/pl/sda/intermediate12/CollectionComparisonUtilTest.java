package pl.sda.intermediate12;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class CollectionComparisonUtilTest {

    @Test
    void compareSimpleCollections() { //zakładamy, że wartości w stringu są tym samym co liczbowe -> "1" == 1
        List<String> list = Lists.newArrayList("1", "2", "3");
        Set<Integer> set = Sets.newHashSet(1, 2, 3);
        CollectionComparisonComparator<String, Integer> stringAndNumberEqualityComparator = null; //todo nalezy okreslic w jaki sposob porownywac napisy i liczby
        CollectionComparisonResult<String, Integer> result = CollectionComparisonUtil.compareCollections(list, set, stringAndNumberEqualityComparator);

        Assertions.assertTrue(result.isSame());
        Assertions.assertEquals(result.getCommon().get("1").intValue(), 1);
    }

    @Test
    public void compareCollectionsOfUsers() {
        List<AnotherUserClass> listA = Lists.newArrayList();
        int iterations = 20000;
        for (int i = 0; i < iterations; i++) {
            AnotherUserClass anotherUserClass = new AnotherUserClass();
            anotherUserClass.setUsername("user" + i + "@wp.pl");
            anotherUserClass.setFirstName("User_" + i + "_FirstName");
            listA.add(anotherUserClass);
        }
        List<User> listB = Lists.newArrayList();
        for (int i = 0; i < iterations + 1; i++) {
            listB.add(populateUser(i));
        }

        long start = System.currentTimeMillis();
        CollectionComparisonComparator<AnotherUserClass, User> usersEqualityComparator = null; //todo nalezy okreslic jak porownywac obiekty jednej i drugiej klasy
        CollectionComparisonResult<AnotherUserClass, User> result = CollectionComparisonUtil.compareCollections(listA, listB, usersEqualityComparator);
        System.out.println("TOOK " + (System.currentTimeMillis() - start));

        Assertions.assertTrue(result.getOnlyInFirst().isEmpty());
        Assertions.assertEquals(1, result.getOnlyInSecond().size());
        Assertions.assertEquals(iterations, result.getCommon().size());
    }

    @Test
    void compareCollectionsOfNumbers() {
        int iterations = 20000;
        List<Integer> listC = Lists.newArrayList();
        for (int i = 0; i < iterations + 3; i++) {
            listC.add(i);
        }
        List<Long> listD = Lists.newArrayList();
        for (long i = 0; i < iterations; i++) {
            listD.add(i);
        }

        long start = System.currentTimeMillis();
        CollectionComparisonComparator<Integer, Long> numbersEqualityComparator = null; //todo należy określić sposób porówania Integer i Long
        CollectionComparisonResult<Integer, Long> result = CollectionComparisonUtil.compareCollections(listC, listD, numbersEqualityComparator);
        System.out.println("TOOK " + (System.currentTimeMillis() - start));

        Assertions.assertEquals(3, result.getOnlyInFirst().size());
        Assertions.assertTrue(result.getOnlyInSecond().isEmpty());
        Assertions.assertEquals(iterations, result.getCommon().size());
    }

    private User populateUser(int i) {
        User user = new User();
        user.setEMail("user" + i + "@wp.pl");
        user.setFirstName("User_" + i + "_FirstName");
        user.setLastName("User_" + i + "_LastName");
        return user;
    }

    @Test
    public void compareCollectionsOfUsersSecondApproach() {
        int iterations = 20000;

        List<User> listA = Lists.newArrayList();
        for (int i = 0; i < iterations; i++) {
            listA.add(populateUser(i));
        }
        List<User> listB = Lists.newArrayList();
        for (int i = 0; i < iterations + 2; i++) {
            listB.add(populateUser(i));
        }

        long start = System.currentTimeMillis();
        Function<User, String> userCompareValueProvider = null; //todo należy podać sposób porównywania elementów klasy User
        CollectionComparisonResult<User, User> result = CollectionComparisonUtil.compareCollections(listA, listB, userCompareValueProvider);
        System.out.println("TOOK " + (System.currentTimeMillis() - start));

        Assertions.assertEquals(0, result.getOnlyInFirst().size());
        Assertions.assertEquals(2, result.getOnlyInSecond().size());
        Assertions.assertEquals(iterations, result.getCommon().size());
    }

    @Test
    void compareCollectionsOfNumbersSecondApproach() {
        int iterations = 20000;

        List<Number> listC = Lists.newArrayList();
        for (long i = 0; i < iterations; i++) {
            listC.add(i);
        }
        List<Number> listD = Lists.newArrayList();
        for (int i = 0; i < iterations + 1; i++) {
            listD.add(i);
        }

        long start = System.currentTimeMillis();
        Function<Number, Integer> numberCompareValueProvider = null; //todo należy podać sposób porównywania wyciągania wartości możliwej do porównania liczb całkowitych
        CollectionComparisonResult<? extends Number, ? extends Number> result = CollectionComparisonUtil.compareCollections(listC, listD, numberCompareValueProvider);
        System.out.println("TOOK " + (System.currentTimeMillis() - start));

        Assertions.assertTrue(result.getOnlyInFirst().isEmpty());
        Assertions.assertEquals(1, result.getOnlyInSecond().size());
        Assertions.assertEquals(iterations, result.getCommon().size());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class AnotherUserClass {
        private String firstName;
        private String lastName;
        private String username;
    }

}