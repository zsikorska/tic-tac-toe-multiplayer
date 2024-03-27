package pwr.tictactoe;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/websocket")
public class WebSocketServer {
    private static Map<String, Session> allUsers = new HashMap<>();
    private static Map<String, Session> allRooms = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        allUsers.put(session.getId(), session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String[] parts = message.split(":");
        String action = parts[0];
        String data = parts[1];

        if (action.equals("request_to_play")) {
            handleRequestToPlay(data, session);
        } else if (action.equals("playerMoveFromClient")) {
            handlePlayerMoveFromClient(data, session);
        }
    }

    @OnClose
    public void onClose(Session session) {
        allUsers.remove(session.getId());
        removeFromRooms(session);
    }

    private void removeFromRooms(Session session) {
        // Remove the user from allRooms if present
        allRooms.entrySet().removeIf(entry -> entry.getValue().equals(session));
        // Inform opponent if user is in a game
        Session opponent = allRooms.remove(session.getId());
        if (opponent != null) {
            try {
                opponent.getBasicRemote().sendText("opponentLeftMatch");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRequestToPlay(String data, Session session) throws IOException {
        String[] requestData = data.split(",");
        String playerName = requestData[0];

        for (Session opponent : allUsers.values()) {
            if (!opponent.equals(session) && !allRooms.containsValue(opponent)) {
                allRooms.put(session.getId(), opponent);
                allRooms.put(opponent.getId(), session);

                String opponentName = opponent.getId();
                session.getBasicRemote().sendText("OpponentFound:" + opponentName + ":circle");
                opponent.getBasicRemote().sendText("OpponentFound:" + playerName + ":cross");
                break;
            }
        }

        if (!allRooms.containsKey(session.getId())) {
            session.getBasicRemote().sendText("OpponentNotFound");
        }
    }

    private void handlePlayerMoveFromClient(String data, Session session) throws IOException {
        Session opponent = allRooms.get(session.getId());
        if (opponent != null) {
            opponent.getBasicRemote().sendText("playerMoveFromServer:" + data);
        }
    }
}
