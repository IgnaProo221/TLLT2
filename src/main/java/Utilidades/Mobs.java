package Utilidades;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tlldos.tll2.TLL2;

public class Mobs implements Listener{
    TLL2 plugin;
    public Mobs(TLL2 plugin){
        this.plugin = plugin;
    }

    public void zombiTest(Zombie self) {
        //Nombre
        self.setCustomName("Zombi Renegado");

        //Armor
        self.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        self.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        self.getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_SWORD));
        self.getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD));
        //Data
        self.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000,2));
        self.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(TLL2.class), "TESTZOMBIE"), PersistentDataType.STRING, "TESTZOMBIE");
        //Etc..
    }

}
