package game.pool;

import java.util.concurrent.ConcurrentHashMap;

public class GamePool {
    private final ConcurrentHashMap<Long, GameSession> gamePool = new ConcurrentHashMap<>();

    public void addToGamePool(GameSession gameSession) {
        gamePool.put(gameSession.getGameId(), gameSession);
    }

    public ConcurrentHashMap<Long, GameSession> getGamePool() {
        return gamePool;
    }
}
