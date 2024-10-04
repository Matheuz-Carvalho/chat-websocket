package org.projetos;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends WebSocketServer {
    private List<WebSocket> clients = new ArrayList<>();

    public ChatServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        clients.add(conn);
        broadcast("Um novo cliente se conectou: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clients.remove(conn);
        broadcast("Um cliente se desconectou: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcast(message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Servidor WebSocket iniciado na porta: " + getPort());
    }

    public static void main(String[] args) {
        int port = 12345; // Porta do servidor
        ChatServer server = new ChatServer(port);
        server.start();
        System.out.println("Servidor WebSocket iniciado na porta: " + port);
    }
}
