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
import team.unnamed.gui.core.gui.type.GUIBuilder;
import tlldos.tll2.TLL2;

import java.util.ArrayList;
import java.util.List;

public class GUIs implements Listener{
    private TLL2 plugin;
    public GUIs(TLL2 plugin){
        this.plugin = plugin;
    }




    ItemStack a = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);




    GUIBuilder ca = GUIBuilder.builder("Sacrifios",3);
    public GUIBuilder getCa() {
        ca.addItem(ItemClickable.builder(0).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(1).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(2).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(3).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(4).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(5).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(6).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(7).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(8).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(9).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(10).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(11).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(12).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(13).setItemStack(new ItemStack(infosacrifios())).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(14).setItemStack(new ItemStack(a)).setAction(event -> true).build());
        ca.addItem(ItemClickable.builder(15).setItemStack(new ItemStack(a)).setAction(event -> true).build());

        return ca;
    }

    public ItemStack infosacrifios(){
        List<String> sinfolore = new ArrayList<String>();
        sinfolore.add(ChatColor.GRAY + "Los Sacrificios se hacen con el Cuchillo Ceremonial");
        sinfolore.add(ChatColor.GRAY + "Cada Sacrificio otorga una recompensa Aleatoria y Bloodstone");
        sinfolore.add(ChatColor.GRAY + "Esto sera penalizado quitando 1 contenedor entero de vida");
        sinfolore.add(ChatColor.GRAY + "Tienes un Limite de quitar 5 corazones");
        sinfolore.add(ChatColor.GRAY + "Has Hecho " + Contador.ContadorS.contador + "de 5");
        ItemStack sinfo = new ItemStack(Material.RED_DYE);
        ItemMeta sinfom = sinfo.getItemMeta();
        sinfom.setDisplayName(ChatColor.RED + "Info Sacrificos");
        return sinfo;
    }
}
