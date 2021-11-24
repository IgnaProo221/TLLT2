package Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import tlldos.tll2.TLL2;

import java.util.Objects;

public class DataGUI implements Listener {
    String nameGUI;
    int size;
    public Inventory gui;

    public DataGUI(String nameGUI, int slots) {
        this.nameGUI = nameGUI;
        this.size = slots;
        this.gui = Bukkit.createInventory(null, slots, nameGUI);

    }

    public void addItemToGUI(int slot, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(TLL2.getPlugin(), "IS_IN_GUI"), PersistentDataType.BYTE, (byte)1);
        this.gui.setItem(slot, item);

    }

    public void addAirSlots(int[] slots) {
        for (int slot : slots) {
            this.gui.setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }

    public boolean isItemCustom(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(TLL2.getPlugin(), "IS_IN_GUI"), PersistentDataType.BYTE);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (Objects.equals(event.getClickedInventory(), this.gui)) {
            if (isItemCustom(Objects.requireNonNull(event.getCurrentItem()))) {

                //Action
            } else {
                event.setCancelled(true);
            }
        }
    }




}
