package game.pool;

import java.util.concurrent.ConcurrentHashMap;

public class GamePool {
    private final ConcurrentHashMap<Long, GameSession> gamePoolMap = new ConcurrentHashMap<>();

    public void addToGamePool(GameSession gameSession) {
        gamePoolMap.put(gameSession.getGameId(), gameSession);
    }

    public ConcurrentHashMap<Long, GameSession> getGamePoolMap() {
        return gamePoolMap;
    }

    public void clearGamePool(){
        gamePoolMap.clear();
    }
}
