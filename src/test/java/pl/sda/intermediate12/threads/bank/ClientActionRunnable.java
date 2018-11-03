package pl.sda.intermediate12.threads.bank;

import java.util.Random;

public class ClientActionRunnable implements Runnable{
    @Override
    public void run() {
        Integer randomAmount = new Random().nextInt(101);
        Bank.withdrawMoney(randomAmount);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.depositBack(randomAmount);
    }
}
