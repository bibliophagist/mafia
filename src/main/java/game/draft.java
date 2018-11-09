package game;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class draft {
    public static void main(String[] args) {
        int waitForMinute = 60;
        while (waitForMinute>0){
            long started = System.currentTimeMillis();
            waitForMinute--;
            System.out.println(waitForMinute);
            long elapsed = System.currentTimeMillis() - started;
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(TimeUnit.SECONDS.toMillis(1) - elapsed));
        }

    }

    private static void test() {
        System.out.println("every Sec");
    }
}