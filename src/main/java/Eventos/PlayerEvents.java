package Eventos;

import Extras.EventosItems;
import Extras.Items;
import Extras.Sacrificios;
import Utilidades.Warn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = (Player)event.getPlayer();

        if (p.hasCooldown(Material.AMETHYST_SHARD)) {
            event.setCancelled(true);
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().equals(Items.createDaga())) {
                Sacrificios.start(p);
            }
        }

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(p.getInventory().getItemInMainHand().equals(Items.termometroItem())){
                try{
                    EventosItems.temperatura(p);
                    p.setCooldown(Material.AMETHYST_SHARD, 60);
                }catch (Exception e){
                    e.printStackTrace();
                    Warn.Mutant(e);
                }
            }
        }
    }



}
