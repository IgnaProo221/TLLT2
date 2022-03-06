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
            p.sendMessage( prefix + format("&7¡No puedes dormir de&6&l día&7 o en&6&l tormenta!"));
            e.setCancelled(true);
            return;
        } else {
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    world.setTime(0L);
                    p.damage(0.1);
                    for(Player jugadores : Bukkit.getOnlinePlayers()){
                        if(jugadores.isSleeping()){
                            jugadores.setStatistic(Statistic.TIME_SINCE_REST, 0);
                        }
                        jugadores.sendMessage(ChatColor.translateAlternateColorCodes('&'," " + prefix +  "&7¡El jugador &3&l" + p.getName()+ " &7ha dormido!"));
                        players.clear();
                    }
                }
            },100L);
            p.setStatistic(Statistic.TIME_SINCE_REST, 0);
        }
    }
}
