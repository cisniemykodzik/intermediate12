package pl.sda.intermediate12;

import org.junit.jupiter.api.Test;

import java.util.*;

public class TestOfTests {

    @Test
    void trickyArrayList() {
        List<Integer> integers =
                Arrays.asList(123123123, 234235324, 34563456);
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<int[]> b = Arrays.asList(new int[]{1, 2, 3});
        System.out.println(a.size());
        System.out.println(b.size());
    }
}
