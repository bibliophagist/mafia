package game.pool;

import java.util.concurrent.atomic.AtomicLong;

public class GameSession {
    private static final AtomicLong atomicLong = new AtomicLong();
    private final long gameId;
    private boolean started = false;

    public GameSession() {
        this.gameId = atomicLong.getAndIncrement();
    }

    public long getGameId() {
        return gameId;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return started;
    }

    /**
     * This method for testing only.
     *
     * @return Current number of GameSessions + 1
     */
    public static AtomicLong getAtomicLong() {
        return atomicLong;
    }
}
