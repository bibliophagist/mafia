package game;

import game.pool.GameSession;
import game.pool.NewGameHandler;

import java.util.concurrent.ConcurrentHashMap;

public class draft {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(NewGameHandler.connectToGame("q"));
        }

        ConcurrentHashMap<Long, GameSession> concurrentHashMap = NewGameHandler.getGamePool().getGamePool();
        for (Long aLong : concurrentHashMap.keySet()) {
            System.out.println(aLong);
        }
    }
}
