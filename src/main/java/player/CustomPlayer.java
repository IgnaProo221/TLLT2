package player;

import java.util.HashMap;
import java.util.UUID;

public class CustomPlayer {
    private static final HashMap<String, CustomPlayer> players = new HashMap<>();

    private final UUID id;
    private final String name;
    private final PlayerData data;

    public CustomPlayer(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.data = new PlayerData(name, id);
    }

    public UUID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerData getData() {
        return data;
    }

    public static CustomPlayer fromName(String name) {
        return players.get(name);
    }

    public static CustomPlayer addPlayer(String name, UUID id) {
        CustomPlayer player = new CustomPlayer(id, name);
        players.put(name, player);

        return player;
    }

    public static void removePlayer(String name) {
        players.remove(name);
    }
}
