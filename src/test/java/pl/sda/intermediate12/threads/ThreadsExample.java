package pl.sda.intermediate12.threads;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate12.threads.bank.Bank;
import pl.sda.intermediate12.threads.bank.ClientActionRunnable;

import java.util.ArrayList;
import java.util.List;

public class ThreadsExample {


    @Test
    void bankExampleSynchronized(){
        for (int i = 0; i < 1000; i++) {
            new ClientActionRunnable().run();
        }
    }

    @Test
    void bankExampleSeparateThreads(){
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(new ClientActionRunnable()));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Bilans na koniec: " + Bank.getBalance());
        System.out.println("Bilans atomowy na koniec: " + Bank.getAtomicBalance());
        System.out.println("Liczba operacji: " + Bank.getCounter());
    }

    @Test
    void runnableBasics() {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() +" Lambda runnable");
        Runnable runnableAnonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +" Anonymous runnable");
            }
        };
        Runnable ourRunnable = new OurRunnable();
        runnable.run();
        runnableAnonymous.run();
        ourRunnable.run();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnableAnonymous);
        Thread thread3 = new Thread(ourRunnable);
        thread1.start();
        thread2.start();
        thread3.start();

    }

    private class OurRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Our Class runnable");
        }
    }
}
