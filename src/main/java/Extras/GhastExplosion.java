package Extras;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class GhastExplosion implements Listener{
    private TLL2 plugin;

    public GhastExplosion(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void GhastExplosionPower(ProjectileLaunchEvent e) {
        var shooter = e.getEntity().getShooter();
        var projectile = e.getEntity();
        if(shooter instanceof Ghast) {
            var ghast = (Ghast) e.getEntity();
            if (ghast.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DESERT_GHAST"), PersistentDataType.STRING)) {
                if (projectile instanceof Fireball) {
                    var fireball = (Fireball) e.getEntity();
                    fireball.setYield(5);
                }
            }
        }
    }
}
