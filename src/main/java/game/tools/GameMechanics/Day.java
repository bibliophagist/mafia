package game.tools.GameMechanics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Day {
    private final SendMessageToClient sendMessageToClient = new SendMessageToClient();
    private static final Logger log = LoggerFactory.getLogger(Day.class);
    private static final int discussTime = 5;
    private NightResults nightResults;

    public Day() {
    }

    public void oneOrdinaryDay() {
        nightResults();
        discussing();
        voting();
    }

    private void discussing() {
        try {
            Thread.sleep(TimeUnit.MINUTES.toMillis(discussTime));
        } catch (InterruptedException e) {
            log.error("Discussing was interrupted", e);
        }
    }

    private void nightResults() {

    }

    private void voting() {
        int waitForMinute = 60;
        while (waitForMinute > 0) {
            long started = System.currentTimeMillis();
            waitForMinute--;
            sendMessageToClient.send();
            long elapsed = System.currentTimeMillis() - started;
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(TimeUnit.SECONDS.toMillis(1) - elapsed));
        }
    }

    public void setNightResults(NightResults nightResults) {
        this.nightResults = nightResults;
    }
}
