package Eventos;

import Extras.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.io.IOException;
import java.util.List;

public class Comer implements Listener {
    private TLL2 plugin;
    public Comer(TLL2 plugin){
        this.plugin = plugin;
    }
    String prefix = ChatColor.translateAlternateColorCodes('&',"&6&lThec&lLast&6&lLife &7➤ ");
    @EventHandler
    public void comerEv(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        if(e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "Fungal Clumps")){
            if (plugin.getConfig().getStringList("ConsumidodoFungalClumps").contains(p.getUniqueId().toString())) {
                p.sendMessage(prefix + ChatColor.RED + "Ya has consumido este Item!");
            }else{
                p.setMaxHealth(24);
                p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cHas Consumido &5Fungal Clumps &7(Se Añadieron 2 Contenedores de Vida)"));
                List<String> lista = plugin.getConfig().getStringList("ConsumidodoFungalClumps");
                lista.add(p.getUniqueId().toString());

                plugin.getConfig().set("ConsumidodoFungalClumps", lista);
                plugin.saveConfig();
        }
    }
        if(e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(ChatColor.WHITE + "" + ChatColor.BOLD + "Cloudy Marshmallow")){
            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200,2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 400, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1, 0));
        }
}
}
