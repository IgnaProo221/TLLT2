package Eventos;

import Utilidades.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
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
    public void zTest(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskTimer(Utils.getPlugin(), ()->{
            if (Utils.getWorld().isThundering()) {
                long segundos = (long) (Utils.getWorld().getWeatherDuration() / 20);
                long hours = segundos  / 3600L;
                long minutes = segundos % 3600L / 60L;
                long seconds = segundos % 60L;

                String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                String s = ChatColor.GRAY + "ZTestXD: " + time;
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(s));

            }
        },0L,20L);
    }
}
