package Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import tlldos.tll2.TLL2;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static Utilities.Format.format;

public class SleepListeners implements Listener{
    private TLL2 plugin;

    public SleepListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    String prefix = ChatColor.translateAlternateColorCodes('&',"&6&lThe&c&lLast&6&lLife &7➤ ");
    private Set<UUID> players = new HashSet<UUID>();


    @EventHandler
    public void onBed(PlayerBedEnterEvent e){
        World world = Bukkit.getWorld("world");
        Player p = e.getPlayer();
        if(world.isDayTime() || world.isThundering()){
            p.sendMessage( prefix + format("&e¡Has reiniciado tus Phantoms!"));
            p.setStatistic(Statistic.TIME_SINCE_REST, 0);
            e.setCancelled(true);
            return;
        } else {
            p.sendMessage(prefix + format("&e¡Has reiniciado tus Phantoms!"));
            p.setStatistic(Statistic.TIME_SINCE_REST, 0);
            e.setCancelled(true);
        } //Setndi
    }
}
