package Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

public class TormentaUtils implements Listener{
    private TLL2 plugin;
    public TormentaUtils(TLL2 plugin){
        this.plugin = plugin;
    }

   ///NOSE COMO HACER ESTO @LEPEPOS AYUDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    public static Player TormentaTier1(Player p){
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0, false, false, false));
        p.playSound(p.getLocation().clone(), Sound.ENTITY_WITHER_SPAWN, 10.0F, -1.0F);

        return p;
    }


}
