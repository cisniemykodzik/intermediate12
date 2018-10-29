package pl.sda.intermediate12.threads;

import org.junit.jupiter.api.Test;

public class ThreadsExample {


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
