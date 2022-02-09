package Extras;

import Utilidades.Format;
import Utilidades.TotemsBar;
import Utilidades.Warn;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

public class EventosItems {

    private final TLL2 plugin;

    public EventosItems(TLL2 plugin){
        this.plugin = plugin;
    }

    public static void temperatura(Player p) {
      try{
          p.sendMessage(Format.PREFIX, format("&cTEMPERATURA TEST: TIENES 69 GRADOS LOL XD"));
      }catch (Exception e){
          e.printStackTrace();
          Warn.Mutant(e);
      }
    }

    public static void totemrestorerEvent(Player p, TLL2 plugin) {
        try{
            p.sendMessage(Format.PREFIX, format("&cSe Han Reiniciado el Porcentaje de Totems Correctamente!"));
            p.playEffect(EntityEffect.TOTEM_RESURRECT);
            p.getPersistentDataContainer().set(new NamespacedKey(plugin, "TOTEM_BAR"), PersistentDataType.INTEGER, 100);
            Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), () -> {
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
            }, 20);
            Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), () -> {
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
            }, 40);
        }catch (Exception e){
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }
    public static void crystalHealthup(Player p){
        if(p.getHealth() < 40) {
            p.sendMessage(Format.PREFIX, format("&c¡Has aumentado tu Vida Maxima!"));
            p.setMaxHealth(p.getMaxHealth() + 2);
            p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 10.0F, -1.0F);
        }else{
            p.sendMessage(Format.PREFIX, format("&c¡No puedes activar mas tu vida maxima!"));
        }
    }
    public static void discordxd(Player p){
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200,2, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200,1, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200,1, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200,0, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200,0, true, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1200,1, true, false, true));
        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 10.0F, 2.0F);
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 10.0F, 2.0F)
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200,2, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200,1, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200,1, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200,0, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200,0, true, false, true))
        );
        p.getLocation().getNearbyPlayers( 10, 10, 10).forEach(player ->
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1200,1, true, false, true))
        );
    }
}
