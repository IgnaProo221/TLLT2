package Eventos;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tlldos.tll2.TLL2;

public class AlEntrar implements Listener {
    TLL2 plugin;
    public static BossBar tormenta;
    public AlEntrar (TLL2 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void barjaGay(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.getName().contains("Barjaxd")){
            p.kickPlayer(ChatColor.RED + "CALLATE BARJA GAY XD");
        }
    }

    @EventHandler
    public void facuPuto(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.getName().contains("Fcun")){
            p.kickPlayer(ChatColor.RED + "No.");
        }
    }
    @EventHandler
    public void tormentaJoin(PlayerJoinEvent e){
        Muerte.tormenta.addPlayer(e.getPlayer());
    }
}
