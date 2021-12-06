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

import java.util.List;
import java.util.Objects;

public class DataGUI implements Listener {
    public String nameGUI;
    public int size;
    public Inventory gui;

    public DataGUI(String nameGUI, int slots) {
        this.nameGUI = nameGUI;
        this.size = slots;
        this.gui = Bukkit.createInventory(null, slots, nameGUI);

    }
    public void addItemToGUI(int slot, ItemStack item) {
        this.gui.setItem(slot, item);
    }

    public void addAirSlots(int[] slots) {
        for (int slot : slots) {
            this.gui.setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
    }




}
