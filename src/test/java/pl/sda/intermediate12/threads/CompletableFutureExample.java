package pl.sda.intermediate12.threads;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class CompletableFutureExample {

    private Function<String, String> stringToString = u -> u;
    private Function<BigDecimal, String> bigDecimalToString = u -> u.toPlainString();
    private Function<Long, String> longToString = u -> u.toString();

    private <T> String tranform(T value, Function<T, String> function) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value);
        return function.apply(value);
    }

    @Test
    public void oneByOne() {
        tranform(downloadDescription(), stringToString);
        tranform(downloadPhotos(), stringToString);
        tranform(downloadadditionalData(), longToString);
        tranform(downloadPrice(), bigDecimalToString);
    }

    @Test
    void threads() {
        Thread t1 = new Thread(() -> tranform(downloadDescription(), stringToString));
        Thread t2 = new Thread(() -> tranform(downloadPhotos(), stringToString));
        Thread t3 = new Thread(() -> tranform(downloadadditionalData(), longToString));
        Thread t4 = new Thread(() -> tranform(downloadPrice(), bigDecimalToString));

        ArrayList<Thread> threads = Lists.newArrayList(t1, t2, t3, t4);

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join(); //czekamy zakończenie pracy wątku
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void futures() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> descr = executorService.submit(() -> downloadDescription());
        Future<String> photos = executorService.submit(() -> downloadPhotos());
        Future<Long> addData = executorService.submit(() -> downloadadditionalData());
        Future<BigDecimal> price = executorService.submit(() -> downloadPrice());

        executorService.submit(() -> tranform(descr.get(), stringToString));
        executorService.submit(() -> tranform(photos.get(), stringToString));
        executorService.submit(() -> tranform(addData.get(), longToString));
        executorService.submit(() -> tranform(price.get(), bigDecimalToString));

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
    }


    @Test
    void completableFutures() {
        CompletableFuture<String> descriptionSource1 = CompletableFuture.supplyAsync(this::downloadDescription);
        CompletableFuture<String> descriptionSource2 = CompletableFuture.supplyAsync(this::downloadDescription2);
        CompletableFuture<String> descrCF = descriptionSource1.applyToEitherAsync(descriptionSource2, e -> e).thenApplyAsync(e->tranform(e,stringToString));
        CompletableFuture<String> phCF = CompletableFuture.supplyAsync(() -> downloadPhotos()).thenApplyAsync(e->tranform(e,stringToString));
        CompletableFuture<String> adataCF = CompletableFuture.supplyAsync(() -> downloadadditionalData()).thenApplyAsync(e->tranform(e,longToString));
        CompletableFuture<String> priceCF = CompletableFuture.supplyAsync(() -> downloadPrice()).thenApplyAsync(e->tranform(e,bigDecimalToString));

        List<CompletableFuture<String>> completableFutureList = Arrays.asList(descrCF, phCF, adataCF, priceCF);

        for (CompletableFuture<String> cf : completableFutureList) {
            cf.join();
        }
    }

    private String downloadDescription() {
        simulateDelay(2000);
        return "Opis";
    }

    private String downloadDescription2() {
        simulateDelay(3000);
        return "Opis2";
    }

    private BigDecimal downloadPrice() {
        simulateDelay(2500);
        return BigDecimal.valueOf(3000.2);
    }

    private String downloadPhotos() {
        simulateDelay(3000);
        return "Zdjęcia";
    }

    private Long downloadadditionalData() {
        simulateDelay(3300);
        return 3000L;
    }

    private void simulateDelay(Integer milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AllArgsConstructor
    @ToString
    private class ProductForTest {
        private String description;
        private String price;
        private String photos;
        private String additionalData;
    }


}

