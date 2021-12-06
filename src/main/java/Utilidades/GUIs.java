package Utilidades;

import Eventos.Muerte;
import net.minecraft.world.item.ItemSaddle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.GUIListeners;
import team.unnamed.gui.core.gui.type.GUIBuilder;
import tlldos.tll2.TLL2;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class GUIs implements Listener{
    private TLL2 plugin;
    public GUIs(TLL2 plugin){
        this.plugin = plugin;
    }










    public static GUIBuilder getCa() {
        GUIBuilder ca = GUIBuilder.builder("Sacrifios",3);

        ca.addItem(ItemClickable.builder(0).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(1).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(2).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(3).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(4).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(5).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(6).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(7).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(8).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(9).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(10).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(11).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(12).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(13).setItemStack(new ItemStack(GUIsItems.infosacrifios())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(14).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(15).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(16).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(17).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(18).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(19).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(20).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(21).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(22).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(23).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(24).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(25).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(26).setItemStack(new ItemStack(GUIsItems.glpa())).setAction(event -> true).build());


        return ca;
    }

}
