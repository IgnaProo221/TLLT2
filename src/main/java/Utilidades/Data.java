package Utilidades;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

public class Data {
    public static PersistentDataContainer get(Player p){
        return p.getPersistentDataContainer();
    }
}
