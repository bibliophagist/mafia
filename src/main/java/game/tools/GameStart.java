package game.tools;

import game.player.Player;
import game.player.Role;
import game.tools.GameMechanics.GameMechanics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class GameStart {
    private static final Logger log = LoggerFactory.getLogger(GameStart.class);
    private final int mafiaNumber;
    private final int townsmanNumber;
    private final GameSession gameSession;

    public GameStart(final GameSession gameSession) {
        mafiaNumber = gameSession.getPlayers().size() / 3;
        townsmanNumber = gameSession.getPlayers().size() - mafiaNumber - 3;
        this.gameSession = gameSession;
        setRoles();
    }

    public void start() {
        GameMechanics gameMechanics = new GameMechanics(gameSession);
        log.info("Game with id {} was started", gameSession.getGameId());
        Thread mainGameTread = new Thread(gameMechanics, "Game number " + gameSession.getGameId());
        log.debug("Thread for player queue was started");
        mainGameTread.start();
    }

    private void setRoles() {
        HashMap<String, Player> players = gameSession.getPlayers();
        HashMap<Role, Boolean> roleExist = new HashMap<>();
        for (Role role : Role.values()) {
            roleExist.put(role, false);
        }
        int mafiaInGame = 0;
        int townsmanInGame = 0;
        for (String key : players.keySet()) {
            Player player = players.get(key);
            while (player.getRole() == null) {
                Role randomRole = Role.randomRole();
                if (!roleExist.get(randomRole)) {
                    log.info("Player {} is {}!", player.getName(), randomRole);
                    player.setRole(randomRole);
                    if (randomRole != Role.MAFIA && randomRole != Role.TOWNSMAN) {
                        roleExist.replace(randomRole, true);
                    } else if (randomRole == Role.MAFIA) {
                        mafiaInGame++;
                        if (mafiaInGame == mafiaNumber) {
                            roleExist.replace(randomRole, true);
                        }
                    } else {
                        townsmanInGame++;
                        if (townsmanInGame == townsmanNumber) {
                            roleExist.replace(randomRole, true);
                        }
                    }
                }
            }
        }
    }

    public void testSetRoles() {
        setRoles();
    }
}
