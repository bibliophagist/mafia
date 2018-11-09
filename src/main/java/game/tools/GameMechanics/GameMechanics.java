package game.tools.GameMechanics;

import game.player.Player;
import game.player.Role;
import game.tools.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class GameMechanics implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(GameMechanics.class);

    private final GameSession gameSession;
    private int mafiaAlive;
    private int playersAlive;
    private final Day day = new Day();
    private final Night night = new Night();

    public GameMechanics(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    @Override
    public void run() {
        while (!gameSession.isFinished()) {
            checkForGameEnd(gameSession.getPlayers());
            day.oneOrdinaryDay();
            checkForGameEnd(gameSession.getPlayers());
            day.setNightResults(night.OneOrdinaryNight());
        }
    }

    private void checkForGameEnd(HashMap<String, Player> players) {
        log.info("Check for game end!");
        int mafiaAlive = 0;
        int playersAlive = 0;
        for (String playerName : players.keySet()) {
            if (players.get(playerName).isAlive()) {
                playersAlive++;
                if (players.get(playerName).getRole() == Role.MAFIA) {
                    mafiaAlive++;
                }
            }
        }
        if ((playersAlive / 2) <= mafiaAlive) {
            gameSession.setFinished(true);
        }
    }

    public void mafiaWasKilled() {
        mafiaAlive--;
    }

    public void playerWasKilled() {
        playersAlive--;
    }

}
