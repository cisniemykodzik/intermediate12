package pl.sda.intermediate12.threads.bank;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Bank {
    private static Integer balance =10000;

    private static AtomicInteger atomicBalance = new AtomicInteger(10000);

    private static int counter;

    public static /*synchronized*/ void withdrawMoney(Integer howMuch){
        balance -= howMuch;
        atomicBalance.getAndUpdate(x -> x - howMuch);
        System.out.println(Thread.currentThread().getName() + " stan konta po wypłacie: " + balance);
    }

    public static /*synchronized*/ void depositBack(Integer howMuch){
        balance += howMuch;  //nie zadziała poprawnie, ponieważ operacje zachodzą współbieżnie, zadziała z 'synchronized'
        atomicBalance.getAndUpdate(x -> x + howMuch);
        System.out.println(Thread.currentThread().getName() + " stan konta po wpłacie: " + balance);
        counter++;
    }

    public static Integer getBalance() {
        return balance;
    }

    public static int getCounter() {
        return counter;
    }

    public static AtomicInteger getAtomicBalance() {
        return atomicBalance;
    }

}
