package Eventos;

import Utilidades.Format;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import java.util.List;

import static Utilidades.Format.PREFIX;
import static Utilidades.Format.format;

public class Comer implements Listener {
    private TLL2 plugin;
    String prefix = ChatColor.translateAlternateColorCodes('&',"&6&lThe&c&lLast&6&lLife &7➤ ");

    public Comer(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void comerEv(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        var data = p.getPersistentDataContainer();
        var temperature = data.get(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER);
        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }else if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }else if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }
        }
        if(e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(ChatColor.GRAY + "Fungal Clumps")){
            if (plugin.getConfig().getStringList("ConsumidodoFungalClumps").contains(p.getUniqueId().toString())) {
                p.sendMessage(prefix + ChatColor.RED + "Ya has consumido este item.");
            }else{
                p.setMaxHealth(24);
                p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', "&cHas consumido la &5Fungal Clumps &7(Se Añadieron 2 Contenedores de Vida)."));
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


        if(e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&bCooler Fruit"))){
            data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature - 10);
            p.sendMessage(PREFIX, format("&b¡Tu temperatura bajo 10°!"));
        }
        if(e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&6Hot Fruit"))){
            data.set(new NamespacedKey(plugin, "temperatura"), PersistentDataType.INTEGER, temperature + 10);
            p.sendMessage(PREFIX, format("&6¡Tu temperatura subió 10°!"));
        }
}
}
