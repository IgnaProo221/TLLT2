package Eventos;

import Extras.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import tlldos.tll2.TLL2;

public class Comer implements Listener {
    private TLL2 plugin;
    public Comer(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void comerEv(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        if(e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "Fungal Clumps")){
            p.setMaxHealth(24);
        }
    }
}
