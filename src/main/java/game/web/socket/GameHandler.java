package game.web.socket;

import game.tools.NewGameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.Map;

@Component
public class GameHandler extends TextWebSocketHandler implements WebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        LOGGER.debug("Web socket connection established, session {}", session);
        URI uri = session.getUri();
        if (uri != null) {
            Map<String, String> queryMap = new QueryMap(uri).getQueryMap();
            LOGGER.debug("Player with login {} connected to the game {}", queryMap.get("login"), queryMap.get("gameId"));
            NewGameHandler.getGamePool().getGamePoolMap().get(Long.decode(queryMap.get("gameId")))
                    .playerJoined(queryMap.get("login"));
        } else {
            LOGGER.error("Uri is null!");
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        LOGGER.debug("Message {} received in session {}", textMessage, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        LOGGER.debug("Web socket connection in session {} closed, status {}", session, closeStatus);
    }
}
