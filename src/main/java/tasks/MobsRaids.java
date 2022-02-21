package tasks;

import Utilities.GameEvents;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tlldos.tll2.TLL2;

import java.util.Random;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class MobsRaids extends BukkitRunnable{
    private final TLL2 plugin;

    public MobsRaids(TLL2 plugin){
        this.plugin = plugin;
    }

    @Override
    public void run(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getGameMode() == GameMode.SURVIVAL){
                Random r = new Random();
                int chancelol = r.nextInt(100000);
                Location l = player.getLocation().clone();
                if (chancelol == 1 && !GameEvents.BlightedRage.isStarted()) {

                    player.sendTitle(format("&6&l¡Blighted Rage!"), format("&7&lEVENTO"), 0, 100, 0);
                    player.sendMessage(PREFIX, format("&c¡Se aproxima un evento de mobs en todos los Jugadores, ¡Afilen sus armas!"));

                    GameEvents.BlightedRage.startEvent(3);

                    //nose como hacer esto xd lol, Buena suerte. (CARROT)

                }
            }
        }
    }
}
