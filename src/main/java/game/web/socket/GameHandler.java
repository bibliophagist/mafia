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

    private static final Logger log = LoggerFactory.getLogger(GameHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.debug("Web socket connection established, session {}", session);
        URI uri = session.getUri();
        if (uri != null) {
            Map<String, String> queryMap = new QueryMap(uri).getQueryMap();
            log.debug("Player with login {} connected to the game {}", queryMap.get("login"), queryMap.get("gameId"));
            NewGameHandler.getGamePool().getGamePoolMap().get(Long.decode(queryMap.get("gameId")))
                    .playerJoined(queryMap.get("login"));
        } else {
            log.error("Uri is null!");
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        log.debug("Message {} received in session {}", textMessage, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        log.debug("Web socket connection in session {} closed, status {}", session, closeStatus);
    }
}
