package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class GameRoom {
    public String id;
    public String name;
    public int[][] world;
    public Map<String, Player> players = new ConcurrentHashMap<>();

    GameRoom(String id, String name, int[][] world) {
        this.id = id;
        this.name = name;
        this.world = world;
    }

    void addPlayer(Player player) {
        players.put(player.id, player);
    }

    void removePlayer(String playerId) {
        players.remove(playerId);
    }

    Player getPlayer(String playerId) {
        return players.get(playerId);
    }

    List<Map<String, Object>> getPlayersData() {
        List<Map<String, Object>> data = new ArrayList<>();
        for (Player player : players.values()) {
            data.add(player.toMap());
        }
        return data;
    }

    static class MapData {
        String levelName;   // primera línea
        String vh;          // segunda línea
        String gv;          // tercera línea
        int[][] world;      // matriz del mapa
    }

    public static MapData loadMap(File file) {
        MapData data = new MapData();
        List<int[]> rows = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {

            // ----- 1. NOMBRE DEL NIVEL -----
            if (sc.hasNextLine()) {
                data.levelName = sc.nextLine().trim();
            }

            // ----- 2. METADATA (opcional pero tú la tienes) -----
            if (sc.hasNextLine()) {
                data.vh = sc.nextLine().trim();  // ejemplo: "vh-30.0"
            }
            if (sc.hasNextLine()) {
                data.gv = sc.nextLine().trim();  // ejemplo: "gv-9.0"
            }

            // ----- 3. MATRIZ DEL MUNDO -----
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                int[] row = new int[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    row[i] = Integer.parseInt(parts[i]);
                }

                rows.add(row);
            }

            data.world = rows.toArray(new int[0][]);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
