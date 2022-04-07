package Extras;

import Utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

public class ExplosionListeners implements Listener{
    private TLL2 plugin;

    public ExplosionListeners(TLL2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void putaMierda(ProjectileLaunchEvent e) {
        if(e.getEntity().getShooter() instanceof Ghast){
            Ghast ghast = (Ghast)e.getEntity().getShooter();
            Fireball fireball = (Fireball) e.getEntity();
            if(ghast.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "DESERT_GHAST"), PersistentDataType.STRING)){
                if(e.getEntity() instanceof Fireball){
                    fireball.setYield(8);
                }
            }else if(ghast.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class), "BLIGHTED_GHAST"), PersistentDataType.STRING)) {
                fireball.setYield(12);
            }else if(ghast.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"DIMEN_GHOST"), PersistentDataType.STRING)){
                fireball.setYield(13);
            }else if(ghast.getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(TLL2.class),"NIGHTMARE"), PersistentDataType.STRING)) {
                fireball.setYield(12);
            }else if(ghast.getPersistentDataContainer().has(Utils.key("STARDUST"),PersistentDataType.STRING)){
                fireball.setYield(0);
            }else{
                fireball.setYield(8);
            }
        }
    }
}
