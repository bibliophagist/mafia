package game.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NewGameHandler {

    private static GameSession gameSession;

    private static boolean stopRequested = false;
    private static final GamePool gamePool = new GamePool();
    private static final Logger LOGGER = LoggerFactory.getLogger(NewGameHandler.class);
    private static final ConcurrentLinkedQueue<String> playerQueue = new ConcurrentLinkedQueue<>();

    static {
        setGameSession(new GameSession());
        LOGGER.info("Thread for player queue is started");
        Thread waitingForPlayers = new Thread(() -> {
            while (!stopRequested) {
                if (playerQueue.size() >= 10) {
                    for (int i = 0; i < 10; i++) {
                        playerQueue.poll();
                    }
                    gameStart();
                }
            }
        });
        waitingForPlayers.start();
    }

    private static void gameStart() {
        LOGGER.info("Game with id {} was started", NewGameHandler.gameSession.getGameId());
        NewGameHandler.gameSession.setStarted(true);
        setGameSession(new GameSession());
    }


    public static void setGameSession(GameSession gameSession) {
        LOGGER.debug("Added new GameSession with id {}", gameSession.getGameId());
        gamePool.addToGamePool(gameSession);
        NewGameHandler.gameSession = gameSession;
    }

    public static long connectToGame(String login) {
        LOGGER.debug("Request from {} for connecting to the Game", login);
        playerQueue.add(login);
        return NewGameHandler.gameSession.getGameId();
    }

    public static GamePool getGamePool() {
        return gamePool;
    }

    public static void clearGamePool() {
        gamePool.clearGamePool();
    }
}
