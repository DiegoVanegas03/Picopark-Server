package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {
    String id;
    String username;
    float x = 20;
    float y = 20;
    float velocityY = 0;
    String direction = "stop";
    int moveDirection = 0; // -1 izquierda, 0 parado, 1 derecha
    boolean isOnGround = false;

    float width = 32;  // Ancho del jugador
    float height = 48; // Alto del jugador

    // Lista de jugadores que están encima de este jugador
    Set<String> playersOnTop = new HashSet<>();

    Player(String id, String username) {
        this.id = id;
        this.username = username;
    }

    Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("username", username);
        data.put("direction", direction);
        data.put("x", x);
        data.put("y", y);
        data.put("velocityY", velocityY);
        data.put("isOnGround", isOnGround);
        return data;
    }
}
