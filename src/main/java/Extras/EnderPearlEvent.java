package Extras;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.util.Random;

public class EnderPearlEvent implements Listener{
    private TLL2 plugin;
    public EnderPearlEvent(TLL2 plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void isUsedEnderPearl(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem() != null && e.getItem().hasItemMeta() && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() || e.getItem() != null && e.getItem().hasItemMeta() &&e.getPlayer().getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                if (e.getItem() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 400 || e.getItem() != null && e.getPlayer().getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 400) {
                    e.getPlayer().setCooldown(Material.ENDER_PEARL, 1);
                } else {
                    e.getPlayer().setCooldown(Material.ENDER_PEARL, 100);
                }
            }
        }
    }

    @EventHandler
    public void enderCayo(PlayerTeleportEvent e){
            Player p = (Player)e.getPlayer();
            Random random = new Random();
            int enderfail = random.nextInt(100);
            if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
                if (enderfail < 10){
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "La Enderpearl que Tiraste no te Teletransporto Correctamente!");
                }
                }
            }

}
