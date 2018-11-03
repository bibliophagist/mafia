import game.player.Player;
import game.player.Role;
import game.tools.GameSession;
import game.tools.GameStart;
import game.tools.NewGameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GameTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameTest.class);

    @Test
    public void testSetRole() {
        HashMap<Role, Boolean> roleExist = new HashMap<>();
        for (Role role : Role.values()) {
            roleExist.put(role, false);
        }
        GameSession gameSession = new GameSession();
        for (int i = 0; i < 10; i++) {
            gameSession.addToPlayersList(new Player("test" + i));
        }
        new GameStart(gameSession).testSetRoles();
        boolean allPlayersHaveRoles = true;
        boolean allRolesExist = true;
        int mafiaNumber = gameSession.getPlayers().size() / 3;
        int townsmanNumber = gameSession.getPlayers().size() - mafiaNumber - 3;
        int currentMafiaNumber = 0;
        int currentTownsmanNumber = 0;
        for (String key : gameSession.getPlayers().keySet()) {
            Player player = gameSession.getPlayers().get(key);
            if (player.getRole() != null) {
                roleExist.replace(player.getRole(), true);
                if (player.getRole() == Role.MAFIA) {
                    currentMafiaNumber++;
                } else if (player.getRole() == Role.TOWNSMAN) {
                    currentTownsmanNumber++;
                }
            } else {
                allPlayersHaveRoles = false;
            }
        }
        if (currentMafiaNumber != mafiaNumber) {
            roleExist.replace(Role.MAFIA, false);
        }
        if (currentTownsmanNumber != townsmanNumber) {
            roleExist.replace(Role.TOWNSMAN, false);
        }
        for (Role role : roleExist.keySet()) {
            if (!roleExist.get(role)) {
                allRolesExist = false;
            }
        }
        Assert.assertTrue(allPlayersHaveRoles);
        Assert.assertTrue(allRolesExist);
    }

    @Test
    public void testConnectToGame() {
        for (int i = 0; i < 100; i++) {
            NewGameHandler.connectToGame(new Player("test"));
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            LOGGER.error("Exception was thrown while testing", e);
        }
        Assert.assertEquals(NewGameHandler.getGamePool().getGamePoolMap().size(), GameSession.getAtomicLong().get() - 1);
        NewGameHandler.clearGamePool();
    }

    @Test
    public void testGetGameId() {
        Assert.assertEquals(NewGameHandler.connectToGame(new Player("test")), GameSession.getAtomicLong().get() - 1);
        NewGameHandler.clearGamePool();
    }
}
