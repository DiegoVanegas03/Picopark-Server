package org.server;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameRoom {
    public boolean canUpdate = true;
    public String id;
    public String name;
    public int[][] world;
    public int[][] gameWorld;
    public int[][] waitingRoom;
    public Map<String, Player> players = new ConcurrentHashMap<>();

    public int completedPlayers = 0;

    public int needUsers;

    GameRoom(String id, String name, int needUsers, int[][] world, int[][] waitingRoom) {
        this.id = id;
        this.name = name;
        this.world = waitingRoom;
        this.gameWorld = world;
        this.waitingRoom = waitingRoom;
        this.needUsers = needUsers;
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

    public static RoomConfig loadRoomConfig(String path) {
        try (Reader reader = new FileReader(path)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, RoomConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
