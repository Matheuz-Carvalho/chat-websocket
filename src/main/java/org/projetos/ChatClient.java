package org.projetos;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ChatClient extends WebSocketClient {

    public ChatClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Conectado ao servidor.");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Conexão fechada: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args) {
        try {
            URI serverUri = new URI("ws://localhost:12345");
            ChatClient client = new ChatClient(serverUri);
            client.connectBlocking(); // Espera a conexão

            client.send("Olá, servidor!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
