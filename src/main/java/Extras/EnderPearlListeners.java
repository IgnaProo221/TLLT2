package Extras;

import Utilities.Format;
import Utilities.Mobs;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import tlldos.tll2.TLL2;

import java.util.Random;

public class EnderPearlListeners implements Listener {
    private TLL2 plugin;

    public EnderPearlListeners(TLL2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerTeleportEvent e){
        if(e.getPlayer().getWorld().isThundering()){
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL){
            e.getPlayer().setCooldown(Material.ENDER_PEARL, 200);
        }
        }
    }


    /*@EventHandler
    public void enderCayo(PlayerTeleportEvent e) {
        Player p = (Player) e.getPlayer();
        Random random = new Random();
        int enderfail = random.nextInt(100);
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            if (enderfail < 10) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "La Enderpearl que lanzaste no se teletransporto correctamente.");
            } else if(enderfail < 20){
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX, Format.format("&c&l¡Se genero un &b&lVorticé &c&len tu posición!"));
                var creeprr = p.getLocation().getWorld().spawn(p.getLocation(), Creeper.class);
                Mobs.vortice(creeprr);
            }
        }
    }*/

}
