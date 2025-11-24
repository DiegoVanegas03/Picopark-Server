package org.server;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int port = 8887;
        GameWebSocketServer server = new GameWebSocketServer(port);
        server.start();
    }
}