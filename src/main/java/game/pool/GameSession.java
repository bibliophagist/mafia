package game.pool;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope("prototype")
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
}
