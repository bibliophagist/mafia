package game.tools;

import game.player.Player;
import game.tools.pool.GamePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NewGameHandler {

    private static GameSession gameSession;

    private static final GamePool gamePool = new GamePool();
    private static final Logger LOGGER = LoggerFactory.getLogger(NewGameHandler.class);
    private static final ConcurrentLinkedQueue<Player> playerQueue = new ConcurrentLinkedQueue<>();

    static {
        setGameSession(new GameSession());
        LOGGER.info("Thread for player queue is started");
        new Thread(() -> {
            Thread.currentThread().setName("playerQueue");
            while (!Thread.currentThread().isInterrupted()) {
                if (playerQueue.size() >= 10) {
                    for (int i = 0; i < 10; i++) {
                        gameSession.addToPlayersList(playerQueue.poll());
                    }
                    new GameStart(gameSession).start();

                    setGameSession(new GameSession());
                }
            }
        }).start();
    }

    private static void setGameSession(GameSession gameSession) {
        LOGGER.debug("Added new GameSession with id {}", gameSession.getGameId());
        gamePool.addToGamePool(gameSession);
        NewGameHandler.gameSession = gameSession;
    }

    public static long connectToGame(Player player) {
        LOGGER.debug("Request from {} for connecting to the Game", player.getName());
        playerQueue.offer(player);
        return NewGameHandler.gameSession.getGameId();
    }

    public static GamePool getGamePool() {
        return gamePool;
    }

    public static void clearGamePool() {
        gamePool.clearGamePool();
    }
}
