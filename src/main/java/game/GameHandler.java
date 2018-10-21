package game;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class GameHandler extends TextWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session){

    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage){

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){

    }
}
