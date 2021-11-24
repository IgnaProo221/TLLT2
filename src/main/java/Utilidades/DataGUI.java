package Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DataGUI {
    String nameGUI;
    int size;
    public Inventory gui;

    public DataGUI(String nameGUI, int slot) {
        this.nameGUI = nameGUI;
        this.size = slot;
        this.gui = Bukkit.createInventory(null, slot, nameGUI);

    }

    public void addItemToGUI(int slot, ItemStack item) {
        this.gui.setItem(slot, item);
    }

    public void addAirSlots(int[] slots) {
        // Dou
       // this.gui.setItem(slots, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
    }

}
