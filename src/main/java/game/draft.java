package game;

import game.player.Player;
import game.tools.GameSession;
import game.tools.GameStart;

public class draft {
    public static void main(String[] args) {
        GameSession gameSession = new GameSession();
        for (int i = 0; i < 10; i++) {
            gameSession.addToPlayersList(new Player("test" + i));
        }
        GameStart gameStart = new GameStart(gameSession);
        for (Player player : gameSession.getPlayers()) {
            System.out.println(player.getRole());
        }

    }
}