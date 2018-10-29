package pl.sda.intermediate12;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class ProteinesChecker {
    @RepeatedTest(1)
    public void parseAndCheck() {
        for (int i =1;i<100000;i++) {
            try {
                Scanner scan = new Scanner(new File("C:\\projects\\12\\mycode\\intermediate12\\src\\test\\resources\\proteines.txt"));
                while (scan.hasNextLine()) {
                    String lineOne = scan.nextLine();
                    String secondLine = scan.nextLine();
                    List<Integer> firstList = lineOne.chars().sorted().mapToObj(a -> Integer.valueOf(a)).collect(toList());
                    List<Integer> secondList = secondLine.chars().sorted().mapToObj(a -> Integer.valueOf(a)).collect(toList());
                    System.out.println(firstList.equals(secondList));

    //                char[] first = lineOne.toCharArray();
    //                char[] second = secondLine.toCharArray();
    //                Arrays.sort(first);
    //                Arrays.sort(second);
    //                System.out.println(lineOne);
    //                System.out.println(secondLine);
    //                System.out.println(first);
    //                System.out.println(second);
    //                System.out.println(Arrays.equals(first, second));

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
