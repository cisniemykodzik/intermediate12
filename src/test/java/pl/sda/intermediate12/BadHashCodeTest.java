package pl.sda.intermediate12;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BadHashCodeTest {
    @RepeatedTest(100)
    public void checkIfObjectsWithBadHashCodeImplementationCanBeAddedTwicelyToSet() {
        Set<Car> carsSet = new HashSet<>();
        carsSet.add(new Car("123123123123", "Audi"));
        carsSet.add(new Car("123123123123", "Audi"));
        carsSet.add(new Car("123123123123", "Audi"));
        carsSet.add(new Car("123123123123", "Audi"));

        System.out.println(carsSet.size());
    }

    @AllArgsConstructor
    private class Car {
        private String vin;
        private String model;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Car car = (Car) o;

            return vin != null ? vin.equals(car.vin) : car.vin == null;
        }

        @Override
        public int hashCode() {
//            return 1;// performance problem
            return new Random().nextInt(100);// very big problem
        }
    }
}