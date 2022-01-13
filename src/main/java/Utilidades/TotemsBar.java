package Utilidades;

import Eventos.Muerte;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class TotemsBar implements Listener {
    public static HashMap<String, Integer> totempercentage = new HashMap<>();
    public static int looseNumber = getLooseCount();

    public static void anadirTC(Player p) {
        String name = p.getName();
        if (totempercentage.containsKey(name)) {
            int oldCount = totempercentage.get(name);
            int result = oldCount + looseNumber;
            totempercentage.remove(name);
            totempercentage.put(name, result);
        } else {
            totempercentage.put(name, looseNumber);
        }
    }

    public static int getPorcentaje(Player player) {
        int max = 100;
        if (totempercentage.get(player.getName()) == null) {
            return 100;
        } else {
            return max - totempercentage.get(player.getName());
        }
    }

    public static void resetAll() {
        totempercentage.clear();
    }

    public static int getLooseCount() {

        int day = Muerte.Dia();

        if (day < 6) {
            return 5;
        } else if (day > 6 && day < 12) {
            return 10;
        } else {
            return 0;
        }
    }

}
