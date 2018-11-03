package game.tools;

import game.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class GameSession {
    private static final AtomicLong atomicLong = new AtomicLong();
    private final long gameId;
    private boolean started = false;
    private boolean finished = false;
    private final HashMap<String, Player> players = new HashMap<>(10);
    private final ArrayList<Player> playersJoined = new ArrayList<>(10);

    public GameSession() {
        this.gameId = atomicLong.getAndIncrement();
    }

    public void addToPlayersList(Player player) {
        players.put(player.getName(), player);
    }

    public void playerJoined(String login) {
        playersJoined.add(players.get(login));
    }

    public HashMap<String, Player> getPlayers() {
        return players;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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
