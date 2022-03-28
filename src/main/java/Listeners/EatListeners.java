package Listeners;

import Utilities.Format;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import player.CustomPlayer;
import player.PlayerData;
import tlldos.tll2.TLL2;

import java.util.Random;

import static Utilities.Format.PREFIX;
import static Utilities.Format.format;

public class EatListeners implements Listener {
    private TLL2 plugin;
    String prefix = ChatColor.translateAlternateColorCodes('&',"&6&lThe&c&lLast&6&lLife &7➤ ");

    public EatListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void comerEv(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        PlayerData data = CustomPlayer.fromName(p.getName()).getData();
        var temperature = data.getTemperature();

        if(p.getWorld().isThundering() && BlastStormListeners.isEnabled()){
            applyRandomEffects(p);
        }
        if(e.getItem().getType() == Material.MILK_BUCKET){
            e.setCancelled(true);
        }

        if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 2) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            } else if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 3) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            } else if (p.getPotionEffect(PotionEffectType.LUCK).getAmplifier() == 4) {
                e.setCancelled(true);
                p.sendMessage(Format.PREFIX + format("&c¡Estas paniquiado!"));
            }
        }

        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&d&lPhantom Heart"))) {
           p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,1200,4,true,false,true));
        }
        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&bCooler Fruit"))) {
            data.setTemperature(temperature - 10);
            p.sendMessage(PREFIX, format("&b¡Tu temperatura bajo 10°!"));
        }
        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&6Hot Fruit"))) {
            data.setTemperature(temperature + 10);
            p.sendMessage(PREFIX, format("&6¡Tu temperatura subió 10°!"));
        }
        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&6&lMagma Pie"))) {
            data.setTemperature(temperature + 30);
            p.sendMessage(PREFIX, format("&6¡Tu temperatura subió 30°!"));
        }
        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&b&lIce Pie"))) {
            data.setTemperature(temperature - 30);
            p.sendMessage(PREFIX, format("&b¡Tu temperatura bajo 30°!"));
        }
        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&cBrimstone"))) {
            p.sendMessage(PREFIX, format("&c&lHas Aumentado tu Daño y Vida Maxima!"));
            int ehp = data.getExtraHealth();
            data.setExtraHealth(ehp + 2);
            p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 0.60);
        }
        if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().contains(format("&7Crystal Apple"))) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 5, true, false, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2, true, false, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,1,true,false,true));
        }
    }

    public void applyRandomEffects(Player p){
        int effect = new Random().nextInt(15);
        if(effect == 1){
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,200,0,true,false,true));
        }else if(effect == 2){
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,200,0,true,false,true));
        }else if(effect == 3){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,200,0,true,false,true));
        }else if(effect == 4){
            p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,0,true,false,true));
        }else if(effect == 5){
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,200,0,true,false,true));
        }else if(effect == 6){
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,200,0,true,false,true));
        }else if(effect == 7){
            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,200,0,true,false,true));
        }else if(effect == 8){
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,0,true,false,true));
        }else if(effect == 9){
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,200,0,true,false,true));
        }else if(effect == 10){
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,200,0,true,false,true));
        }else if(effect == 11){
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,200,0,true,false,true));
        }else if(effect == 12){
            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,200,0,true,false,true));
        }else if(effect == 13){
            p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,200,0,true,false,true));
        }else if(effect == 14){
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,200,0,true,false,true));
        }else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,200,0,true,false,true));
        }

    }

}
