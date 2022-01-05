package Utilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class TotemsBar implements Listener {
    public static HashMap<String, Integer> totempercentage = new HashMap<>();

    public void anadirTC(Player p) {
        String name = p.getName();
        if (totempercentage.containsKey(name)) {
            int oldCount = totempercentage.get(name);
            int result = oldCount + 1;
            totempercentage.remove(name);
            totempercentage.put(name, result);
        } else {
            totempercentage.put(name, 1);
        }
    }

}
