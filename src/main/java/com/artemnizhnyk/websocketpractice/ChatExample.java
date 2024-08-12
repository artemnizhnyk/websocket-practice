package com.artemnizhnyk.websocketpractice;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.logging.Level;

@Slf4j
@ServerEndpoint("/websocketchat")
public class ChatExample {

    @OnOpen
    public void connectionOpened() {
        log.info("connection opened");
    }

    @OnMessage
    public synchronized void processMessage(Session session,
                                            String message) {
        log.info("received message: {}", message);
        session.getOpenSessions().forEach(sess -> {
            if (sess.isOpen()) {
                try {
                    sess.getBasicRemote().sendText(message);
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        });
    }

    @OnClose
    public void connectionClosed() {
        log.info("connection closed");
    }
}