package Utilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class TotemsBar implements Listener {
    public static HashMap<String, Integer> percentage = new HashMap<>();
    public static int looseNumber = getLooseCount();

    public static void anadirTC(Player p) {
        String name = p.getName();
        if (percentage.containsKey(name)) {
            int oldCount = percentage.get(name);
            int result = oldCount - looseNumber;
            percentage.remove(name);
            percentage.put(name, result);
        } else {
            percentage.put(name, looseNumber);
        }
    }

    public static int getPorcentaje(Player player) {
        int max = 100;
        if (percentage.get(player.getName()) == null) {
            return 100;
        } else {
            return max - percentage.get(player.getName());
        }
    }

    public static void resetAll() {
        percentage.clear();
    }

    public static int getLooseCount() {

        int day = Utils.getDay();

        if (day < 6) {
            return 5;
        } else if (day > 6 && day < 12) {
            return 10;
        } else {
            return 0;
        }
    }

}
